# coding:utf-8

from kivy.app import App
from kivy.uix.image import Image
from kivy.clock import Clock
from kivy.graphics.texture import Texture
from kivy.uix.button import Button
from kivy.uix.floatlayout import FloatLayout
from kivy.config import Config

import cv2
import numpy as np
import inspect

Config.set('input', 'mouse', 'mouse,multitouch_on_demand')      #Disable multi touch (ie orange dots on the IHM)


class Segment:
    def __init__(self, segments=4):
        # define number of segments, with default 4
        self.segments = segments
        self.nbrOfAnaglyph = 0

    def kmeans(self, image):
        # image=cv2.GaussianBlur(image,(7,7), 0)
        vectorized = image.reshape(-1, 3)
        vectorized = np.float32(vectorized)  # Image formatted in numpy 2D array

        # Criteria : Stop iteration after specified nbMAX of iterations OR accuracy reached, nbMAX, accuracy. Used after
        criteria = (cv2.TERM_CRITERIA_EPS + cv2.TERM_CRITERIA_MAX_ITER, 20, 0.5)

        # Kmeans(DATA, nb of clusters (colors), bestLabels, stop_criteria, attempts, labels_type)
        ret, label, center = cv2.kmeans(vectorized, self.segments, None, criteria, 1, cv2.KMEANS_RANDOM_CENTERS)

        # Reshape
        res = center[label.flatten()]
        segmented_image = res.reshape(image.shape)
        return label.reshape((image.shape[0], image.shape[1])), segmented_image.astype(np.uint8)

    def extractComponent(self, image, label_image, label):
        # Extraction of 1 component depending on the label from kmeans clustering
        component = np.full(image.shape, 255, np.uint8)
        component[label_image == label] = image[label_image == label]
        return component

    def shift2D(self, inputIm, horizontalShift, verticalShift, valueBGR):
        # Shifting the inputImage left or right, vertical or horizontal depending on parameters
        num_rows, num_cols = inputIm.shape[:2]

        translation_matrix = np.float32([[1, 0, horizontalShift], [0, 1, verticalShift]])
        img_translation = cv2.warpAffine(inputIm, translation_matrix, (num_cols, num_rows), borderValue=valueBGR)
        return img_translation

    def changeColor(self, image, label, currentLabel, color):
        # This function modifies the color of pixels determined by kmeans clustering
        if color == 1:
            image[np.where(label == currentLabel)] = [0, 0, 255]  # Red conversion
            return image
        else:
            image[np.where(label == currentLabel)] = [255, 255, 0]  # Cyan conversion
            return image

    def transfo3D(self, image):

        gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
        # hist = cv2.calcHist([gray], [0], None, [256], [0, 256])
        # plt.hist(gray.ravel(),256,[0,256]) # Uncomment to show histogram

        (retVal, newImg) = cv2.threshold(gray, 0, 255, cv2.THRESH_BINARY + cv2.THRESH_OTSU)
        image[np.where(newImg == 255)] = [255, 255, 255]
        # cv2.imwrite("binary.png", newImg)
        # cv2.imwrite("newImage.png", image)

        label, result = self.kmeans(image)

        # cv2.imwrite("resultat_final.png", result)

        imageList = []
        nbrElements = 0
        for i in range(0, 4):
            # In order to suppress the only-white cluster
            if np.any(image[np.where(label == i)] != [255, 255, 255]):
                result = self.extractComponent(image, label, i)  # Extraction of each color (ie white picture with cluster)

                cv2.imwrite("color" + str(i) + ".png", result)  # Writing the images in the folder to preview
                if np.any(nbrElements == 0):
                    redImage = self.changeColor(result, label, i, 1)
                    imageList.append(self.shift2D(redImage, 13, 0, [255, 255, 255]))  # Shift Left
                    cyanImage = self.changeColor(result, label, i, 0)
                    imageList.append(self.shift2D(cyanImage, -13, 0, [255, 255, 255]))  # Shift Right
                elif np.any(nbrElements == 1):
                    redImage = self.changeColor(result, label, i, 1)
                    imageList.append(self.shift2D(redImage, 3, 0, [255, 255, 255]))  # Shift Right
                    cyanImage = self.changeColor(result, label, i, 0)
                    imageList.append(self.shift2D(cyanImage, -3, 0, [255, 255, 255]))  # Shift Left
                else:
                    redImage = self.changeColor(result, label, i, 1)
                    imageList.append(self.shift2D(redImage, 5, 0, [255, 255, 255]))  # Shift Left
                    cyanImage = self.changeColor(result, label, i, 0)
                    imageList.append(self.shift2D(cyanImage, -5, 0, [255, 255, 255]))  # Shift Right
                nbrElements += 1

        final = cv2.bitwise_and(imageList[0], imageList[1])  # Final image initialize
        # Superposition of all the red/cyan layers
        for j in range(0, len(imageList)):
            final = cv2.bitwise_and(final, imageList[j])

        # Conversion from portrait to landscape picture
        # final = cv2.transpose(final)
        # cv2.flip(final, 0, final)
        self.nbrOfAnaglyph += 1
        return final
        # cv2.imwrite("final.png", final)  # Writing the images in the folder to preview


# This class is the camera
class KivyCamera(Image):

    def __init__(self, capture, fps,  **kwargs):
        super(KivyCamera, self).__init__(**kwargs)
        self.capture = capture
        self.fps = fps
        self.activateClock(True)

    def activateClock(self, activate):
        if activate == True:
            Clock.schedule_interval(self.update, 1.0 / self.fps)
        else:
            Clock.unschedule(self.update)

    def setImage(self, img):
        self.img = img

    def unfilteredTexture(self, frame):
        # parent = str(inspect.stack()[1][3])
        buf1 = cv2.flip(frame, 0)
        buf = buf1.tostring()

        image_texture = Texture.create(size=(frame.shape[1], frame.shape[0]), colorfmt='bgr')
        image_texture.blit_buffer(buf, colorfmt='bgr', bufferfmt='ubyte')
        self.texture = image_texture   

    def diplayImage(self, image):
        self.unfilteredTexture(image)

    def update(self, dt):
        # The update function read a new frame, does the faceDetect process and apply the filter
        ret, frame = self.capture.read()
        self.unfilteredTexture(frame)

#This class is the IHM widget of the app. It contains buttons, video, ...
class CamApp(App):
    def __init__(self):
        super(CamApp, self).__init__()
        self.capture = cv2.VideoCapture(0)
        self.capture.set(3, 1920)
        self.capture.set(4, 1080)
        self.my_camera = KivyCamera(capture=self.capture, allow_stretch=True,fps=30)
        self.Segment = Segment()

    def get_image(self):
        # read is the easiest way to get a full image out of a VideoCapture object.
        retval, im = self.capture.read()
        return im
    # This function choose the behaviour of the "filters" button when clicked
    def btn_click(self, component):
        if "Transformer en 3D" in component.text:
            self.my_camera.activateClock(False)
            component.text = "Retour"
            anaglyphe = self.Segment.transfo3D(self.get_image())
            cv2.imwrite("anaglyphe_" + str(self.Segment.nbrOfAnaglyph) + ".jpg", anaglyphe)
            self.my_camera.diplayImage(anaglyphe)
        elif "Retour" in component.text:
            component.text = "Transformer en 3D"
            self.my_camera.activateClock(True)

    # This function build the widget to be used as IHM
    def build(self):
        root = FloatLayout()                                                                    # Creation d'une FloatBOX principale
        root.add_widget(self.my_camera)                                                         # Ajout de la vidéo dans la BOX principale
        mainbutton = Button(text='Transformer en 3D', size_hint=(1, 0.08), background_color = (2,0,0,1))  # Create a big main button
        mainbutton.bind(on_release=self.btn_click)
        root.add_widget(mainbutton)
        return root                                                     # root is all the environnement with the video, buttons, etc

    def on_stop(self):
        # Without this, the app will not exit even if the window is closed
        self.capture.release()

#Launch the program
if __name__ == '__main__':
    CamApp().run()

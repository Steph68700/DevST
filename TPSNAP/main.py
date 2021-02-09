# coding:utf-8

from kivy.app import App
from kivy.uix.image import Image
from kivy.clock import Clock
from kivy.graphics.texture import Texture
from kivy.uix.dropdown import DropDown
from kivy.uix.button import Button
from kivy.uix.floatlayout import FloatLayout
from kivy.lang import Builder
from PIL import Image as PILimage
from PIL import ImageOps

import cv2
import sys
import numpy
import os
import pathlib

#			***** Search and create a list off all canevas in the ./Canevas Directorie and a list of implemented filters *****

# This class contains parameters for canevas
class canevas:
	"""This class represent a canevas for the app TPSNAP. It is composed by :
		- name : name of the canevas used for the button in the IHM
		- GetName : a function to extract the name of the canevas from its fullPath
		- fullPath : path of the *.png
		- type : caracterise the canevas. It can be a Hat, a Face, a Body or a Scene canevas
		- GetType : a function to extract the type of canevas using its fullPath
		- offset : to shift the canevas (Must be given in the canevas name)
		- GetOffset : a function to extract the offset of the canevas using its fullPath"""

	# Extract the name of canevas using its fullPath
	def GetName(self, fullPath):
		splitFullPath = fullPath.split("/")
		splitName = splitFullPath[len(splitFullPath) - 1].split("$")
		return splitName[0].replace(".png", "")

	# Extract the type of canevas using its fullPath
	def GetType(self, fullPath):
		if "Hat" in fullPath:
			return "HAT"
		elif "Face" in fullPath:
			return "FACE"
		elif "Body" in fullPath:
			return "BODY"
		elif "Scene" in fullPath:
			return "Scene"
		else: 
			return "NONE"		
	
	# Extract the offset value from the fullPath of the canevas (ie from "___$XX,xx.png" or "___$XX.xx.png" extract "XX.xx" with "___" random caracters composing the path
	def GetOffset(self, path):
		splitString = path.split("$")
		if(len(splitString) > 1):			
			try:
				return float(splitString[len(splitString)-1].replace(".png","").replace(",", ".")) 	#Try to parse the value between "$" and ".png"
			except ValueError:
				return 0
		else:
			return 0

	# Describe canevas object in a string
	def ToString(self):
		return "This canevas object contains : \n\t- name = " + self.name + "\n\t- fullPath = " + self.fullPath + "\n\t- type = " + self.type + "\n\t- offset = " + str(self.offset)
	
	# Constructor of canevas object
	def __init__(self, fullPath):
		self.fullPath = fullPath
		self.name = self.GetName(fullPath)
		self.type = self.GetType(fullPath)
		self.offset = self.GetOffset(fullPath)


# Create the list of all the canevas
canevasList = []

# Find all hat canevas
for currentFile in pathlib.Path('./Canevas/Hat').glob("*.png"):  
    canevasList.append(canevas("./" + str(currentFile)))

# Find all face canevas
for currentFile in pathlib.Path('./Canevas/Face').glob("*.png"):  
   	canevasList.append(canevas("./" + str(currentFile)))

# Find all body canevas
for currentFile in pathlib.Path('./Canevas/Body').glob("*.png"):  
   	canevasList.append(canevas("./" + str(currentFile)))

# Find all scene canevas
for currentFile in pathlib.Path('./Canevas/Scene').glob("*.png"):  
   	canevasList.append(canevas("./" + str(currentFile)))

#Create the list of all the filters using the canevas object. The type will be "NONE"
filtersList = [canevas("Normal"), canevas("Sépia")]

#			***** Search and create a list off all canevas in the ./Canevas Directories *****

# This class is the "image processing" class, in there, we are doing the FaceDetect and filter applying
class KivyCamera(Image):

    def __init__(self, capture, fps,  **kwargs):
        super(KivyCamera, self).__init__(**kwargs)
        self.capture = capture
        self.currentCanevas = None
        # Load the file who helps for the face detect process.
        self.faceCascade = cv2.CascadeClassifier("haarcascade_frontalface_default.xml")
        Clock.schedule_interval(self.update, 1.0 / fps)

    # Sepia filter applying    
    def sepiaFilter(self, Frame):
        frame = PILimage.fromarray(Frame)                           # Convert frame to PILimage type
        if frame.mode != "L":
            frame = frame.convert("L")                              # Convert in GrayScale Pil image

        frame = ImageOps.autocontrast(frame)
        frame.putpalette(self.sepia)                                # Sepia filter application
        sepia_img = frame.convert("RGB")                            # Convert frame to RGB type
        sepia_img = numpy.array(sepia_img)                          # Convert to numpy array

        buf1 = cv2.flip(sepia_img, 0)                               # Flip frame in order to display it further
        buf2 = cv2.flip(buf1, 1) 
        buf = buf2.tostring()
        image_texture = Texture.create(size=(sepia_img.shape[1], sepia_img.shape[0]), colorfmt='rgb')  # Create Texture, size of the frame
        image_texture.blit_buffer(buf, colorfmt='rgb', bufferfmt='ubyte')  # Filling the texture with the buffer containig the frame
        # Display image from the texture
        self.texture = image_texture

    def make_linear_ramp(white):
    # Putpalette expects [r,g,b,r,g,b,...]
    # Sepia filter contruction by linear ramp
        ramp = []
        r, g, b = white
        for i in range(255):
            ramp.extend((r*i//255, g*i//255, b*i//255))
        return ramp

    sepia = make_linear_ramp((255, 240, 192)) # This coefficients allows sepia convertion

    def unfilteredTexture(self, frame):
        buf1 = cv2.flip(frame, 0)
        buf2 = cv2.flip(buf1, 1)
        buf = buf2.tostring()
        image_texture = Texture.create(size=(frame.shape[1], frame.shape[0]), colorfmt='bgr')
        image_texture.blit_buffer(buf, colorfmt='bgr', bufferfmt='ubyte')
        self.texture = image_texture

    def update(self, dt):
        # The update function read a new frame, does the faceDetect process and apply the filter
        ret, frame = self.capture.read()
        if self.currentCanevas is None:         # Display image without effect                    
            self.unfilteredTexture(frame)
        elif ret and self.currentCanevas.type != "NONE":    # Display image canevas 
            
            # Convert the frame in shades of gray (for face detection purpose)
            gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

            # Detect faces on the gray frame
            faces = self.faceCascade.detectMultiScale(
                gray,
                scaleFactor=1.3,                        # This factor is just a scaling factor
                minNeighbors=6,                         # Increase this to decrease artifacts (more neighbors = more chances to be a face)
                minSize=(30, 30),                       # Min size of a face (distance)
                flags=cv2.CASCADE_SCALE_IMAGE
            )
            
            for (x, y, w, h) in faces:
                # Drow rectangles around faces, un-comment if you want them to appear
                # cv2.rectangle(frame, (x, y), (x+w, y+h), (255, 0, 0), 2)

                # Resizing the future filter image
                resized_img = cv2.resize(cv2.imread(self.currentCanevas.fullPath, -1), (w, h))              

                # Apply the offset
                y = y - int(self.currentCanevas.offset * resized_img.shape[0])           
               
                # Cutting the filter when it reaches the top of the screen
                if y < 0:
                    diff = -y
                    y = 0
                    resized_img = resized_img[diff:, :]

                y1, y2 = y, y + resized_img.shape[0]                    # Positionning the filter Y
                x1, x2 = x, x + resized_img.shape[1]                    # Positionning the filter X
                alpha_s = resized_img[:, :, 3] / 255.0                  # Removing the background
                alpha_l = 1.0 - alpha_s                                 # Same as before
                
                # Reconstruction of the frame, especially the part where we place the filter
                for c in range(0, 3):
                    frame[y1:y2, x1:x2, c] = (alpha_s * resized_img[:, :, c] +
                                              alpha_l * frame[y1:y2, x1:x2, c])
            
            buf1 = cv2.flip(frame, 0)
            buf2 = cv2.flip(buf1, 1)
            buf = buf2.tostring()
            image_texture = Texture.create(size=(frame.shape[1], frame.shape[0]), colorfmt='bgr')
            image_texture.blit_buffer(buf, colorfmt='bgr', bufferfmt='ubyte')
            # display image from the texture
            self.texture = image_texture

        elif ret and self.currentCanevas.type == "NONE":        # Display filtered image
            if self.currentCanevas.name == "Sépia":
                self.sepiaFilter(frame)
            elif self.currentCanevas.name == "Normal":
                self.unfilteredTexture(frame)


# This class is the IHM widget of the app. It contains buttons, video, ...
class CamApp(App):
    def __init__(self, CanevasList, FiltersList):
        super(CamApp, self).__init__()
        self.allCanevas = CanevasList
        self.filtersNames = FiltersList
        self.dropdown = DropDown()
        self.capture = cv2.VideoCapture(0)
        self.my_camera = KivyCamera(capture=self.capture, allow_stretch=True, fps=30)  

    # This function choose the behaviour of the "filters" button when clicked
    def btn_click(self, component):                                                      
        self.dropdown.open(component)  # Open the canevas list selector                                                                                        

    # This function build the widget to be used as IHM
    def build(self):
        root = FloatLayout()                                                            # Creation d'une FloatBOX principale
        root.add_widget(self.my_camera)                                                 # Ajout de la vidéo dans la BOX principale

        # Creation of "small" buttons, one for each canevas
        index = 0
        for canevas in self.allCanevas:            
            index += 1
            btn1 = Button(text=canevas.name , size_hint_y=None, height=24, pos=(20,20), background_color = (index,1,1,0.9))
            btn1.bind(on_release=lambda btn1: self.dropdown.select(btn1.text))

            # Add the button inside the dropdown
            self.dropdown.add_widget(btn1)

        # Creation of "small" buttons, one for each filter
        for filters in self.filtersNames:
            index += 1            
            btn1 = Button(text=filters.name , size_hint_y=None, height=24, pos=(20,20), background_color = (index,1,1,0.9))
            btn1.bind(on_release=lambda btn1: self.dropdown.select(btn1.text))

            # Add the button inside the dropdown
            self.dropdown.add_widget(btn1)

        # Create a big main button
        mainbutton = Button(text='Filtres', size_hint=(1, 0.08), background_color = (2,0,0,1))
        mainbutton.bind(on_release=self.btn_click)

        def changeFilter(component, x, **kwargs): 
            setattr(mainbutton, 'text', x)                              # x is the a string containing the name of the selected filter
            items1 = [item for item in self.allCanevas if item.name.upper() == x.upper()]
            items2 = [item for item in self.filtersNames if item.name.upper() == x.upper()]
            if(len(items1) > 0):
                self.my_camera.currentCanevas = items1[0]
            elif(len(items2) > 0):
                self.my_camera.currentCanevas = items2[0] # The type will be none
                
        # Listen for the selection in the dropdown list and
        # assign the data to the button text.
        self.dropdown.bind(on_select=changeFilter)

        root.add_widget(mainbutton)

        return root    # root is all the environnement with the video, buttons, etc

    def on_stop(self):
        # Without this, the app will not exit even if the window is closed
        self.capture.release()

#Launch the program
if __name__ == '__main__':
    CamApp(canevasList, filtersList).run()

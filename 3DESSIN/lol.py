import cv2
import numpy as np
#from matplotlib import pyplot as plt


class Segment:
    def __init__(self, segments=4):
        # define number of segments, with default 4
        self.segments = segments

    def kmeans(self, image):
        #image=cv2.GaussianBlur(image,(9,9),255)
        vectorized = image.reshape(-1, 3)
        vectorized = np.float32(vectorized)  # image formatted in numpy array
        criteria = (cv2.TERM_CRITERIA_EPS + cv2.TERM_CRITERIA_MAX_ITER, 20,
                    0.5)  # Stop iteration after specified nbMAX of iterations OR accuracy reached, nbMAX, accuracy
        ret, label, center = cv2.kmeans(vectorized, self.segments, None, criteria, 2, cv2.KMEANS_RANDOM_CENTERS)
        # DATA, nb of clusters (colors), bestLabels, stop criteria, attempts, used flags
        res = center[label.flatten()]
        segmented_image = res.reshape((image.shape))
        return label.reshape((image.shape[0], image.shape[1])), segmented_image.astype(np.uint8)

    def extractComponent(self, image, label_image, label):
        component = np.full(image.shape, 255, np.uint8)
        component[label_image == label] = image[label_image == label]
        return component

    def shift2D(self, inputIm, horizontalShift, verticalShift, valueBGR):
        num_rows, num_cols = inputIm.shape[:2]

        translation_matrix = np.float32([[1, 0, horizontalShift], [0, 1, verticalShift]])
        img_translation = cv2.warpAffine(inputIm, translation_matrix, (num_cols, num_rows), borderValue=valueBGR)
        return img_translation

    def changecolor(self, image, label, currentLabel, color):
        if color == 1 :
            image[np.where(label == currentLabel)] = [0, 0, 250]  # Red conversion
            return image
        else:
            image[np.where(label == currentLabel)] = [255, 255, 0]  # Cyan conversion
            return image


if __name__ == "__main__":
    import argparse
    import sys

    ap = argparse.ArgumentParser()
    ap.add_argument("-i", "--image", required=True, help="Path to the image")
    ap.add_argument("-n", "--segments", required=False, type=int,
                    help="# of clusters")
    args = vars(ap.parse_args())

    image = cv2.imread(args["image"])

    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    hist = cv2.calcHist([gray],[0],None,[256],[0,256])
    #plt.hist(gray.ravel(),256,[0,256])

    (retVal, newImg) = cv2.threshold(gray, 140, 255, cv2.THRESH_BINARY)
    image[np.where(newImg == 255)] = [255, 255, 255]

    if len(sys.argv) == 3:

        seg = Segment()
        label, result = seg.kmeans(image)
    else:
        seg = Segment(args["segments"])
        label, result = seg.kmeans(image)

    cv2.imwrite("resultat_final.png", result)
    imageList = []
    final = 0
    nbrElements = 0
    for i in range(0, 4):
        # In order to suppress the only-white cluster
        if np.any(image[np.where(label == i)] != [255, 255, 255]):
            result = seg.extractComponent(image, label, i)  # Extraction of each color (ie white picture with cluster forms)
            #  result[np.where(label == i)] = [0, 0, 250]  # color in red

            cv2.imwrite("color" + str(i) + ".png", result)  # Writing the images in the folder to preview

            if np.any(i == 1):
                redImage = seg.changecolor(result, label, i, 1)
                imageList.append(seg.shift2D(redImage, 0, 20, [255, 255, 255]))  # Shift Left
            elif np.any(i == 2):
                cyanImage = seg.changecolor(result, label, i, 0)
                imageList.append(seg.shift2D(cyanImage, 0, -100, [255, 255, 255]))  # Shift Left
            else:
                redImage = seg.changecolor(result, label, i, 1)
                imageList.append(seg.shift2D(redImage, 0, 20, [255, 255, 255]))  # Shift Left
            nbrElements += 1

            # imageList[nbrElements - 1][np.where(label == i)] = [0, 0, 255]  # Red conversion

            cv2.imwrite("shiftL" + str(i) + ".png", imageList[nbrElements - 1])  #  Writing the images in the folder to preview
            #  cv2.imwrite("colora" + str(i) + ".png", result)  # Writing the images in the folder to preview
            if np.any(i == 1):
                cyanImage = seg.changecolor(result, label, i, 0)
                imageList.append(seg.shift2D(cyanImage, 0, -30, [255, 255, 255]))  # Shift Right
            elif np.any(i == 2):
                redImage = seg.changecolor(result, label, i, 1)
                imageList.append(seg.shift2D(redImage, 0, 100, [255, 255, 255]))  # Shift Right
            else:
                cyanImage = seg.changecolor(result, label, i, 0)
                imageList.append(seg.shift2D(cyanImage, 0, -20, [255, 255, 255]))  # Shift Right
            nbrElements += 1

            # imageList[nbrElements - 1][np.where(label == i)] = [255, 250, 0]  # Cyan conversion
            cv2.imwrite("shiftR" + str(i) + ".png", imageList[nbrElements - 1])  #  Writing the images in the folder to preview

    final = cv2.bitwise_and(imageList[0], imageList[1])
    final = cv2.bitwise_and(final, imageList[2])
    final = cv2.bitwise_and(final, imageList[3])
    final = cv2.bitwise_and(final, imageList[4])
    final = cv2.bitwise_and(final, imageList[5])

    cv2.imwrite("final.png", final)  # Writing the images in the folder to preview

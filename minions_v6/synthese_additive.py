from PIL import Image, ImageDraw

def synthese(red=255, green=255, blue=255):

    background = 0 # noir

    # couche dans les niveau de gris
    image_R = Image.new('L', (450, 450), background)
    image_G = Image.new('L', (450, 450), background)
    image_B = Image.new('L', (450, 450), background)

    # cercle sur la couche rouge
    draw_R = ImageDraw.Draw(image_R)
    draw_R.ellipse((10,150,300,440), red)

    # cercle sur la couche verte
    draw_G = ImageDraw.Draw(image_G)
    draw_G.ellipse((150,150,440,440), green)

    # cercle sur la couche bleu
    draw_B = ImageDraw.Draw(image_B)
    draw_B.ellipse((75,10,375,300), blue)

    #image_R.show()               
    #image_G.show()               
    #image_B.show()

    # image rgb
    image_RGB = Image.merge('RGB', (image_R, image_G, image_B))

    image_RGB.show()               

synthese(255, 255, 255)

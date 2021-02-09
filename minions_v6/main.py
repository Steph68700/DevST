from kivy.uix.floatlayout import FloatLayout
from kivy.uix.slider import Slider
from kivy.app import App
from kivy.core.window import Window
from kivy.uix.widget import Widget
from kivy.properties import StringProperty
from kivy.factory import Factory
from kivy.uix.dropdown import DropDown
from kivy.uix.boxlayout import BoxLayout
from PIL.Image import *
from PIL import Image, ImageDraw, ImageChops
import os


script_dir = os.path.dirname(__file__) #<-- absolute dir the script is in

# Couleur de la fenetre Blanc
Window.clearcolor = (1, 1, 1, 1)

global sr,sg,sb
sr=255
sg=255
sb=255

def synthese(red=255, green=255, blue=255):

#*****************************************************
# Synthese additive: Fusion des calques Niveau de Gris
#*****************************************************
    """
    background = 255 # noir

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
    """

    
#****************************************************************
# Synthese additive: Utilisation de ImageChops
#***************************************************************
    image1 = Image.new("RGBA", (500, 500), color=0)
    image2 = Image.new("RGBA", (500, 500), color=0)
    image3 = Image.new("RGBA", (500, 500), color=0)

    draw1 = ImageDraw.Draw(image1)
    draw2 = ImageDraw.Draw(image2)
    draw3 = ImageDraw.Draw(image3)

    draw1.ellipse([10, 150, 300, 440], (red,0,0))
    draw2.ellipse([150, 150, 440, 440], (0,green,0))
    draw3.ellipse([75, 10, 375, 300], (0,0,blue))

    out = ImageChops.add(image1,image2,0.5)
    out = ImageChops.add(out,image3,0.5)

#***************************************************************
    out.save(os.path.join(script_dir, './Cercle/rgb.png')) #sauvegarder dans une nouvelle imag              

synthese(sr, sg, sb)




# Classe RootWidget Interaction avec l'interface <RootWidget> du fichier kv
class RootWidget(FloatLayout):
    '''This is the class representing your root widget.
       By default it is inherited from BoxLayout,
       you can use any other layout/widget depending on your usage.
    '''

    """******************************************************************"""
    """ Initialisation """
    """******************************************************************"""
    def __init__(self, **kwargs):
        super(RootWidget, self).__init__(**kwargs)

        self.ids.textCercle.text = " Vous pouvez faire varier les curseurs pour modifier l'intensité lumineuse " 
        
        """ Variable global sur la position des images et sliders""" 
        global PosComposanteRed, PosComposanteGreen, PosComposanteBlue, PosSliderRed, PosSliderGreen, PosSliderBlue
        global ImVide, Image
        global sr,sg,sb

        """ Definition de la position des images et sliders """
        PosComposanteRed = {'x' : 0.42, 'y' : 0.79}
        PosComposanteGreen = {'x' : 0.42, 'y' : 0.43}
        PosComposanteBlue = {'x' : 0.42, 'y' : 0.07}

        PosSliderRed = {'x' : 0.4, 'y' : 0.72}
        PosSliderGreen = {'x' : 0.4, 'y' : 0.36}
        PosSliderBlue = {'x' : 0.4, 'y' : 0.0}

        ImVide = os.path.join(script_dir,'./ImgAnnexe/vide.png')
        Image = ''

        sr=self.ids.SlideRed.value
        sg=self.ids.SlideGreen.value
        sb=self.ids.SlideBlue.value

        

        

    """********************************************************************"""
    """ Position pour les Programmes avec des images """
    """********************************************************************"""
    def PosInterface(self):
        self.ids.textCercle.text = " " 
        
        # Suppresion de l'affichage des cercle
        self.ids.cercleRed.source = ImVide
        self.ids.cercleGreen.source = ImVide
        self.ids.cercleBlue.source = ImVide
        self.ids.cercleRGB.source = ImVide

        # afficher la fontion echange
        self.ids.fleche1.source = os.path.join(script_dir,'./ImgAnnexe/fleche.png')
        self.ids.fleche2.source = os.path.join(script_dir,'./ImgAnnexe/fleche.png')
        self.ids.fleche3.source = os.path.join(script_dir,'./ImgAnnexe/fleche.png')
        self.ids.fleche4.source = os.path.join(script_dir,'./ImgAnnexe/fleche.png')
        self.ids.fleche5.source = os.path.join(script_dir,'./ImgAnnexe/fleche.png')
        self.ids.fleche6.source = os.path.join(script_dir,'./ImgAnnexe/fleche.png')
        self.ids.ImageChange1.source= os.path.join(script_dir,'./ImgAnnexe/echange.png')
        self.ids.ImageChange2.source= os.path.join(script_dir,'./ImgAnnexe/echange.png')
        self.ids.change1.size_hint = 0.07, 0.07
        self.ids.change2.size_hint = 0.07, 0.07

        # afficher le texte
        self.ids.LabelOrigine.text = 'Image Originale'
        self.ids.LabelFi.text = 'Image Finale'

        # réinitialisation de la position des sliders
        self.ids.SlideRed.pos_hint = {'x' : 0.4, 'y' : 0.72}
        self.ids.SlideGreen.pos_hint = {'x' : 0.4, 'y' : 0.36}
        self.ids.SlideBlue.pos_hint = {'x' : 0.4, 'y' : 0.0}

        # réinitialisation de la valeur des sliders
        self.ids.SlideRed.value = 255
        self.ids.SlideGreen.value = 255
        self.ids.SlideBlue.value = 255

        # repositionnement du texte
        self.ids.LabelRed.pos_hint = {'x' : 0.5, 'y' : 0.97}
        self.ids.LabelGreen.pos_hint = {'x' : 0.5, 'y' : 0.61}
        self.ids.LabelBlue.pos_hint = {'x' : 0.5, 'y' : 0.25}

        #Réinitialisation de la position de images
        self.ids.ImageRed.pos_hint = PosComposanteRed
        self.ids.ImageGreen.pos_hint = PosComposanteGreen
        self.ids.ImageBlue.pos_hint = PosComposanteBlue


    """*******************************************************"""
    """ Afficher shrek """
    """*******************************************************"""
    def shrek(self):
        Image = os.path.join(script_dir,'./Img/shrek.png')
        self.ids.ImageOrigin.source=Image
        self.ids.ImageRed.source=Image
        self.ids.ImageGreen.source=Image
        self.ids.ImageBlue.source=Image
        self.ids.ImageFi.source=Image

    """*******************************************************"""
    """ Afficher minion """
    """*******************************************************"""
    def minion(self):
        Image = os.path.join(script_dir,'./Img/minions.png')
        self.ids.ImageOrigin.source=Image
        self.ids.ImageRed.source=Image
        self.ids.ImageGreen.source=Image
        self.ids.ImageBlue.source=Image
        self.ids.ImageFi.source=Image

    """*******************************************************"""
    """ Afficher stoumpfette """
    """*******************************************************"""
    def stroumpfette(self):
        Image = os.path.join(script_dir,'./Img/stroumpfette.png')
        self.ids.ImageOrigin.source=Image
        self.ids.ImageRed.source=Image
        self.ids.ImageGreen.source=Image
        self.ids.ImageBlue.source=Image
        self.ids.ImageFi.source=Image

    """*******************************************************"""
    """ Afficher capitaine """
    """*******************************************************"""
    def capitaine(self):
        Image = os.path.join(script_dir,'./Img/captaine.png')
        self.ids.ImageOrigin.source=Image
        self.ids.ImageRed.source=Image
        self.ids.ImageGreen.source=Image
        self.ids.ImageBlue.source=Image
        self.ids.ImageFi.source=Image


    """********************************************************************"""
    """ Position pour le Programme avec les cercles """
    """********************************************************************"""
    def cercle(self):
        global sr,sg,sb
        self.ids.textCercle.text = " Vous pouvez faire varier les curseurs pour modifier l'intensité lumineuse "

        sr=self.ids.SlideRed.value
        sg=self.ids.SlideGreen.value
        sb=self.ids.SlideBlue.value
        
        #masque les images
        self.ids.ImageOrigin.source=ImVide
        self.ids.ImageRed.source=ImVide
        self.ids.ImageGreen.source=ImVide
        self.ids.ImageBlue.source=ImVide
        self.ids.ImageFi.source=ImVide
        self.ids.fleche1.source = ImVide
        self.ids.fleche2.source = ImVide
        self.ids.fleche3.source = ImVide
        self.ids.fleche4.source = ImVide
        self.ids.fleche5.source = ImVide
        self.ids.fleche6.source = ImVide

        # affiche les cercles
        self.ids.cercleRed.source = os.path.join(script_dir,'./Cercle/cercle.png')
        self.ids.cercleGreen.source = os.path.join(script_dir,'./Cercle/cercle.png')
        self.ids.cercleBlue.source = os.path.join(script_dir,'./Cercle/cercle.png')
        self.ids.cercleRGB.source = os.path.join(script_dir,'./Cercle/cer.png')

        #masque la fonction echange
        self.ids.ImageChange1.source=ImVide
        self.ids.ImageChange2.source=ImVide
        self.ids.change1.size_hint = 0.0, 0.0
        self.ids.change2.size_hint = 0.0, 0.0

        # masque le texte
        self.ids.LabelOrigine.text = ''
        self.ids.LabelFi.text = ''

        # repositionne les sliders
        self.ids.SlideRed.pos_hint = {'x' : 0.08, 'y' : 0.2}
        self.ids.SlideGreen.pos_hint = {'x' : 0.32, 'y' : 0.2}
        self.ids.SlideBlue.pos_hint = {'x' : 0.2, 'y' : 0.5}

        # reinitialise les valeurs des sliders
        self.ids.SlideRed.value = 255
        self.ids.SlideGreen.value = 255
        self.ids.SlideBlue.value = 255

        # repositionne le texte
        self.ids.LabelRed.pos_hint = {'x' : 0.18, 'y' : 0.48}
        self.ids.LabelGreen.pos_hint = {'x' : 0.42, 'y' : 0.48}
        self.ids.LabelBlue.pos_hint = {'x' : 0.3, 'y' : 0.78}


    """*************************************************************************************"""
    """ Echange des intensités lumineuse de chaque pixel entre la composante rouge et verte """
    """*************************************************************************************"""
    def change1(self):

        """ Recuperation des positions """
        global PosComposanteRed,PosComposanteGreen,PosComposanteBlue,PosSliderRed,PosSliderGreen,PosSliderBlue

        """ Si l'Image rouge = composante rouge et Image verte = composante verte """
        if  self.ids.ImageRed.pos_hint == PosComposanteRed and self.ids.ImageGreen.pos_hint == PosComposanteGreen :

            """ Echange des Images Rouge et Verte """
            self.ids.ImageRed.pos_hint = PosComposanteGreen
            self.ids.ImageGreen.pos_hint = PosComposanteRed
            self.ids.ImageBlue.pos_hint = PosComposanteBlue

            """ Echange des sliders Rouge et vert """
            self.ids.SlideRed.pos_hint = PosSliderGreen
            self.ids.SlideGreen.pos_hint = PosSliderRed
            self.ids.SlideBlue.pos_hint = PosSliderBlue

            """ Si l'Image rouge = composante rouge et Image bleue = composante verte """
        elif  self.ids.ImageRed.pos_hint == PosComposanteRed and self.ids.ImageBlue.pos_hint == PosComposanteGreen:

            """ Echange des Images Rouge et Bleu """
            self.ids.ImageRed.pos_hint = PosComposanteGreen
            self.ids.ImageGreen.pos_hint = PosComposanteBlue
            self.ids.ImageBlue.pos_hint = PosComposanteRed

            """ Echange des sliders Rouge et vert """
            self.ids.SlideRed.pos_hint = PosSliderGreen
            self.ids.SlideGreen.pos_hint = PosSliderBlue
            self.ids.SlideBlue.pos_hint = PosSliderRed

            """ Si l'Image verte = composante rouge et Image rouge = composante verte """
        elif  self.ids.ImageGreen.pos_hint == PosComposanteRed and self.ids.ImageRed.pos_hint == PosComposanteGreen:

            """ Echange des Images verte et rouge """
            self.ids.ImageRed.pos_hint = PosComposanteRed
            self.ids.ImageGreen.pos_hint = PosComposanteGreen
            self.ids.ImageBlue.pos_hint = PosComposanteBlue

            """ Echange des Sliders verte et rouge """
            self.ids.SlideRed.pos_hint = PosSliderRed
            self.ids.SlideGreen.pos_hint = PosSliderGreen
            self.ids.SlideBlue.pos_hint = PosSliderBlue

            """ Si l'Image verte = composante rouge et Image bleue = composante verte """
        elif  self.ids.ImageGreen.pos_hint == PosComposanteRed and self.ids.ImageBlue.pos_hint == PosComposanteGreen:

            """ Echange des Image verte et Bleue """
            self.ids.ImageRed.pos_hint = PosComposanteBlue
            self.ids.ImageGreen.pos_hint = PosComposanteGreen
            self.ids.ImageBlue.pos_hint = PosComposanteRed

            """ Echange des sliders verte et Bleue """
            self.ids.SlideRed.pos_hint = PosSliderBlue
            self.ids.SlideGreen.pos_hint = PosSliderGreen
            self.ids.SlideBlue.pos_hint = PosSliderRed

            """ Si l'Image bleue = composante rouge et Image rouge = composante verte """
        elif  self.ids.ImageBlue.pos_hint == PosComposanteRed and self.ids.ImageRed.pos_hint == PosComposanteGreen:

            """ Echange des Images bleue et rouge """
            self.ids.ImageRed.pos_hint = PosComposanteRed
            self.ids.ImageGreen.pos_hint = PosComposanteBlue
            self.ids.ImageBlue.pos_hint = PosComposanteGreen

            """ Echange des sliders bleue et rouge """
            self.ids.SlideRed.pos_hint = PosSliderRed
            self.ids.SlideGreen.pos_hint = PosSliderBlue
            self.ids.SlideBlue.pos_hint = PosSliderGreen

            """ Si l'Image bleue = composante rouge et Image verte = composante verte """
        elif  self.ids.ImageBlue.pos_hint == PosComposanteRed and self.ids.ImageGreen.pos_hint == PosComposanteGreen:

            """ Echange des Images bleue et verte """
            self.ids.ImageRed.pos_hint = PosComposanteBlue
            self.ids.ImageGreen.pos_hint = PosComposanteRed
            self.ids.ImageBlue.pos_hint = PosComposanteGreen

            """ Echange des sliders bleue et verte """
            self.ids.SlideRed.pos_hint = PosSliderBlue
            self.ids.SlideGreen.pos_hint = PosSliderRed
            self.ids.SlideBlue.pos_hint = PosSliderGreen

        #Ouvrir l'image
        i = open(os.path.join(script_dir,self.ids.ImageFi.source)).convert("RGBA")
        # Recuperer le nombre de pixel en largeur et hauteur 
        (largeur, hauteur)= i.size

        #Tant que tous les pixels de la largeur ne sont pas traité 
        for x in range(0,largeur):
            #Tant que tous les pixels de la longueur ne sont pas traité
            for y in range(0,hauteur):
                 (rouge,vert,bleu,alpha) = i.getpixel((x,y))  #Obtenir l'intensité des pixels 
                 (rouge,vert,bleu,alpha) = (vert,rouge,bleu,alpha)  #echanger l'intensité des composantes rouge et verte
                 i.putpixel((x,y),(rouge,vert,bleu,alpha)) #remetre les pixels dans l'image
        
        i.save(os.path.join(script_dir,'./Img/temp_image.png'),'PNG') #sauvegarder dans une nouvelle image
        
        self.ids.ImageFi.source = os.path.join(script_dir,'./Img/temp_image.png') # afficher la nouvel image
        
        self.ids.ImageFi.reload() #recharger l'image






    """*************************************************************************************"""
    """ Echange des intensités lumineuse de chaque pixel entre la composante verte et bleue """
    """*************************************************************************************"""
    def change2(self):

        global PosComposanteRed,PosComposanteGreen,PosComposanteBlue,PosSliderRed,PosSliderGreen,PosSliderBlue

        #""" Si l'Image rouge = composante bleu et Image verte = composante verte """
        if  self.ids.ImageRed.pos_hint == PosComposanteBlue and self.ids.ImageGreen.pos_hint == PosComposanteGreen:

            """ echange Image rouge et verte"""
            self.ids.ImageRed.pos_hint = PosComposanteGreen
            self.ids.ImageGreen.pos_hint = PosComposanteBlue
            self.ids.ImageBlue.pos_hint = PosComposanteRed

            """ echange Slider rouge et verte"""
            self.ids.SlideRed.pos_hint = PosSliderGreen
            self.ids.SlideGreen.pos_hint = PosSliderBlue
            self.ids.SlideBlue.pos_hint = PosSliderRed

            """ Si l'Image rouge = composante bleu et Image bleue = composante verte """
        elif  self.ids.ImageRed.pos_hint == PosComposanteBlue and self.ids.ImageBlue.pos_hint == PosComposanteGreen:

            """ echange Image rouge et bleue"""
            self.ids.ImageRed.pos_hint = PosComposanteGreen
            self.ids.ImageGreen.pos_hint = PosComposanteRed
            self.ids.ImageBlue.pos_hint = PosComposanteBlue

            """ echange Slider rouge et bleue"""
            self.ids.SlideRed.pos_hint = PosSliderGreen
            self.ids.SlideGreen.pos_hint = PosSliderRed
            self.ids.SlideBlue.pos_hint = PosSliderBlue

            """ Si l'Image verte = composante bleu et Image rouge = composante verte """
        elif  self.ids.ImageGreen.pos_hint == PosComposanteBlue and self.ids.ImageRed.pos_hint == PosComposanteGreen:

            """ echange Image verte et rouge"""
            self.ids.ImageRed.pos_hint = PosComposanteBlue
            self.ids.ImageGreen.pos_hint = PosComposanteGreen
            self.ids.ImageBlue.pos_hint = PosComposanteRed

            """ echange Slider verte et rouge"""
            self.ids.SlideRed.pos_hint = PosSliderBlue
            self.ids.SlideGreen.pos_hint = PosSliderGreen
            self.ids.SlideBlue.pos_hint = PosSliderRed

            """ Si l'Image verte = composante bleu et Image bleu = composante verte """
        elif  self.ids.ImageGreen.pos_hint == PosComposanteBlue and self.ids.ImageBlue.pos_hint == PosComposanteGreen:

            """ echange Image verte et bleue"""
            self.ids.ImageRed.pos_hint = PosComposanteRed
            self.ids.ImageGreen.pos_hint = PosComposanteGreen
            self.ids.ImageBlue.pos_hint = PosComposanteBlue

            """ echange Image verte et bleue"""
            self.ids.SlideRed.pos_hint = PosSliderRed
            self.ids.SlideGreen.pos_hint = PosSliderGreen
            self.ids.SlideBlue.pos_hint = PosSliderBlue

            """ Si l'Image bleue = composante bleu et Image verte = composante verte """
        elif  self.ids.ImageBlue.pos_hint == PosComposanteBlue and self.ids.ImageRed.pos_hint == PosComposanteGreen:

            """ echange Image bleue et rouge"""
            self.ids.ImageRed.pos_hint = PosComposanteBlue
            self.ids.ImageGreen.pos_hint = PosComposanteRed
            self.ids.ImageBlue.pos_hint = PosComposanteGreen

            """ echange Slider bleue et rouge"""
            self.ids.SlideRed.pos_hint = PosSliderBlue
            self.ids.SlideGreen.pos_hint = PosSliderRed
            self.ids.SlideBlue.pos_hint = PosSliderGreen

           #""" Si l'Image bleue = composante bleu et Image verte = composante verte """
        else:
            """ echange Image bleue et verte"""
            self.ids.ImageRed.pos_hint = PosComposanteRed
            self.ids.ImageGreen.pos_hint = PosComposanteBlue
            self.ids.ImageBlue.pos_hint = PosComposanteGreen

            """ echange Image bleue et verte"""
            self.ids.SlideRed.pos_hint = PosSliderRed
            self.ids.SlideGreen.pos_hint = PosSliderBlue
            self.ids.SlideBlue.pos_hint = PosSliderGreen       

        #Ouvrir l'image
        i = open(os.path.join(script_dir,self.ids.ImageFi.source)).convert("RGBA")

        # Recuperer le nombre de pixel en largeur et hauteur 
        (largeur, hauteur)= i.size

        #Tant que tous les pixels de la largeur ne sont pas traité 
        for x in range(0,largeur):
            #Tant que tous les pixels de la longueur ne sont pas traité
            for y in range(0,hauteur):
                 (rouge,vert,bleu,alpha) = i.getpixel((x,y)) #Obtenir l'intensité des pixels 
                 (rouge,vert,bleu,alpha) = (rouge,bleu,vert,alpha)#echanger l'intensité des composantes rouge et verte
                 i.putpixel((x,y),(rouge,vert,bleu,alpha)) #remetre les pixels dans l'image
        i.save(os.path.join(script_dir,'./Img/temp_image.png'),'PNG') #sauvegarder dans une nouvelle image
        self.ids.ImageFi.source = os.path.join(script_dir,'./Img/temp_image.png') # afficher la nouvel image
        self.ids.ImageFi.reload() #recharger l'image
        
pass

# Classe Main APP (lance le fichier main.kv)
class MainApp(App):
    '''This is the main class of your app.
       Define any app wide entities here.
       This class can be accessed anywhere inside the kivy app as,
       in python::

         app = App.get_running_app()
         print (app.title)

       in kv language::

         on_release: print(app.title)
       Name of the .kv file that is auto-loaded is derived from the name
       of this class::

         MainApp = main.kv
         MainClass = mainclass.kv

       The App part is auto removed and the whole name is lowercased.
    '''

    def build(self):
        return RootWidget()

if '__main__' == __name__:
    MainApp().run()


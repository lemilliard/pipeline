from kivy.app import App

from view.home.home import Home


class PipelineFrontKivyApp(App):
    def build(self):
        return Home()


PipelineFrontKivyApp().run()

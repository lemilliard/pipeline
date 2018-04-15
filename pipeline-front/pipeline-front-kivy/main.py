from kivy.app import App
from kivy.garden.cefpython import CefBrowser


class PipelineFrontKivyApp(App):
    def build(self):
        return CefBrowser(start_url="http://kivy.org")


PipelineFrontKivyApp().run()

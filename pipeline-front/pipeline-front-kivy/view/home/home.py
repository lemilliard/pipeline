from kivy import resources
from kivy.lang import Builder
from kivy.uix.widget import Widget

import os

resources.resource_add_path(os.path.abspath(os.path.join(os.path.dirname(__file__))))
Builder.load_file(resources.resource_find("home.kv"))


class Home(Widget):
    pass

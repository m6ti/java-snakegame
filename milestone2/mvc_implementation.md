# MVC Implementation


## Relocation of classes
- The first thing that I did to work toward implementing MVC, was to split all the classes with multi-functionality up
into classes that only had one purpose, for example splitting the original inner classes of MyFrame. 
- This made it much easier to then group the classes... 

## Sub-packaging for modularity
- Next, I created sub-packages for different class types. This was a step after splitting the code from the resources.
You can view the 'midway' project structure image [here](structure_update.md).
- This was to enforce modularity and make clear sub-packages of models, views, and controllers.

## Post JavaFX implementation
- After replacing Swing with JavaFX, the views side of things was handled by FXML files, stored under 
the resources folder, emptying the views sub-package, which is why it is not found in my final
project structure image found [here](structure_update.md).




##


Go back to [Milestone 2](../milestone2/milestone2.md).

Alternatively, [go back to **project home**](../README.md)

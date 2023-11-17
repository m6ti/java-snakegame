# Milestone 1

## Flow Analysis

We launch the snake game through `play.java`.
When we run the file, we instantiate a new `Play` object.
- After instantiation, a few key *instance variables* are created, namely `mySnake` : MySnake, `food` : Food, `background` : Image, `fail` : Image.
- This Play class also overrides a couple methods from its parent class, MyFrame: `keyPressed()` and `paint()`.
- A novel method is also created called `drawScore()`.

After creation of the object, the program calls a method (from the parents class, MyFrame) called `loadFrame()`.
- loadFrame, simply loads the frame.
- Specifically, it does this:
  - Sets the Play object to use a buffer to paint.
  - Adds the Play object to the JFrame.
  - Adds the Play object as a key listener of the JFrame.
  - Sets title of the frame to “Snakee Yipee”, size to (870,560), location relative to null.
  - Adds a window listener to the JFrame, that listens for if the user exits the frame using the close button.
  - Finally, it sets the frame to visible, and creates a `MyThread` object, and runs `start()` straight away.


## Class Analysis
![Class Diagram](ClassDiagram.jpg)

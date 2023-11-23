# Flow Analysis

We launch the snake game through the main method in`play.java`.
When we run main, we instantiate a new `Play` object.
- After instantiation, a few key *instance variables* are created, namely `mySnake` : MySnake, `food` : Food, `background` : Image, `fail` : Image.
- This Play class also overrides a couple methods from its parent class, MyFrame: `keyPressed()` and `paint()`.
- A novel method is also created called `drawScore()`.
  - `keyPressed` method is overridden to handle key events, forwarding them to the MySnake class for procesing.
  - `paint` method is overridden to draw the game elements.

After creation of the object, the program calls a method (from the parents class, MyFrame) called `loadFrame()`.
- loadFrame, does what its name suggests: loads the frame, and makes it repaint the frame every 30 ms.
- Specifically, it does this:
    - Sets the Play object to use a buffer to paint.
    - Adds the Play object to the JFrame.
    - Adds the Play object as a key listener of the JFrame.
    - Sets title of the frame to “Snakee Yipee”, size to (870,560), location relative to null.
    - Adds a window listener to the JFrame, that listens for if the user exits the frame using the close button.
    - Finally, it sets the frame to visible, and creates a `MyThread` object, and runs `start()` straight away.


- The `MyThread` object extends the Thread class, which schedules the threads execution, independently of the current thread.
- Execution of the thread executes the run() method, which is overridden, and is set to constantly run the repaint method every 30 ms.

- Finally, background music is played using the `MusicPlayer` class.
- The program runs until the user closes the window.

##

Go back to [Milestone 1](../milestone1/milestone1.md).

Alternatively, [go back to **project home**](../README.md)
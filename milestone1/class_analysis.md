# Class Analysis


## Food Usage

Food is a class that when instantiated, creates a random food that the snake will eat.
- It’s only used inside the Play class, which initialises a random food, and then when the food is eaten, it will create another one for the snake to eat, in a random place.


## GameUtil Usage

GameUtil is never instantiated, it is used as a utility class / helper class.
- Good that all the methods are static, meaning they can be called directly on the class.
- Doesn’t have a constructor, so we don’t need to set that to private.
- Could use final in the class (meaning it can’t be subclassed) but this is not necessary.
- The class is used for two things:
    - To get images (takes a path and returns an Image)
    - And to rotate images (takes a bufferedImage and degree of rotation, and returns a rotated image)

## ImageUtil Usage

This class is another utility class that never gets instantiated, and its methods are available for use as they are public and static.
- The class has one attribute, a HashMap which maps Strings to Images.
- It is used inside the food class, in which it generates a random food, then maps it to the image to use for the game.
- Inside the MySnake class, it is used to return an image of the snake head and snake body.

## MusicPlayer Usage

This class is used to… play music… nice. It is also a kind of utility class, however the constructor here is used, and is not private.
- We could change this to private, as the only call to the constructor is from within the class inside the `getMusicPlay` method.
- The getMusicPlay static method is called from the Play class, where it is used to play some music.

## MyFrame Usage

This class is a subclass of JPanel and an interface of KeyListener.
- This class holds a few different nested classes inside of it. I am unsure if they need to be inside the MyFrame class.
- Some of the usages of MyFrame come up as MySnake and SnakeObject usages… (CHECK)
- The constructor uses a very wierd method. CHECK
- Play class also extends/ is a subclass of MyFrame.


## Play Usage 
- Play is instantiated, inside of main, inside the `play.java` file, and then instantly runs loadFrame,
  which is a method of its parent class, MyFrame.

## Movable Usage

Moveable is an interface, which represents a specification, and is implemented by the MySnake class.

## Paddle Usage
- No usage.

## Snake Usage
- No usage of Snake class.

## Main Usage
- No usage

##

Go back to [Milestone 1](../milestone1/milestone1.md).

Alternatively, [go back to **project home**](../README.md)
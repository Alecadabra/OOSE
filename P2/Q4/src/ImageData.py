class ImageData:

    def __init__(width : int, height: int):
        self.image = [width][height]
    
    def setPixel(x : int, y : int, value : int):
        image[x][y] = value

    def getPixel(x : int, y : int) -> int:
        return image[x][y]
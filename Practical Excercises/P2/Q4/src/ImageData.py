class ImageData:

    def __init__(self, width : int, height: int):
        self.image = [width][height]
    
    def setPixel(self, x : int, y : int, value : int):
        image[x][y] = value

    def getPixel(self, x : int, y : int) -> int:
        return image[x][y]
class SimpleImageEditor(ImageEditor):

    def scale(oldImage : ImageData):
        newWidth = oldImage.getWidth() // 2
        newHeight = oldImage.getHeight() // 2
        newImage = ImageData(newWidth, newHeight)

        for(y in range(newHeight)):
            for(x in range(newWidth)):
                newImage.setPixel(x, y, oldImage.getPixel(x * 2, y * 2))

        return newImage

    def rotate(oldImage : ImageData):
        newWidth = oldImage.getHeight()
        newHeight = oldImage.getWidth()
        newImage = ImageData(newWidth, newHeight)

        for(y in range(newHeight)):
            for(x in range(newWidth)):
                newImage.setPixel(x, y, oldImage.getPixel(newHeight - y, x))
        
        return newImage
    
    def invert(oldImage : ImageData):
        newWidth = oldImage.getWidth()
        newHeight = oldImage.getHeight()
        newImage = ImageData(newWidth, newHeight)

        for(y in range(newHeight)):
            for(x in range(newWidth)):
                newImage.setPixel(x, y, ~oldImage.getPiexl(x, y))
        
        return newImage
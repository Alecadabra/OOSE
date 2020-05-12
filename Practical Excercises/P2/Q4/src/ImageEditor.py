class ImageEditor:
    
    def edit(self, image : ImageData) -> ImageData:
        image = scale(image)
        image = rotate(image)
        image = invert(image)

        return image

    def scale(self, oldImage : ImageData):
        raise NotImplementedError

    def rotate(self, oldImage : ImageData):
        raise NotImplementedError

    def invert(self, oldImage : ImageData):
        raise NotImplementedError
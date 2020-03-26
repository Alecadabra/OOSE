class ImageEditor:
    
    def edit(image : ImageData) -> ImageData:
        image = scale(image)
        image = rotate(image)
        image = invert(image)

        return image

    def scale(oldImage : ImageData):
        raise NotImplementedError

    def rotate(oldImage : ImageData):
        raise NotImplementedError

    def invert(oldImage : ImageData):
        raise NotImplementedError
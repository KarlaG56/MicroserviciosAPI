from uuid import uuid4


class Products:
    def __init__(self, name, price, stock):
        self.id = uuid4()
        self.name = name
        self.price = price
        self.stock = stock
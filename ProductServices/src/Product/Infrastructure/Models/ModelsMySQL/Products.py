from src.Database.MySQL import Base
from sqlalchemy import Column, String, Integer, Float


class Products(Base):
    __tablename__ = 'products'
    id = Column(String(36), primary_key=True)
    name = Column(String(255), nullable=False)
    price = Column(Float, nullable=False)
    stock = Column(Integer, nullable=False)

    def to_dict(self):
        return {
            "id": self.id,
            "name": self.name,
            "price": self.price,
            "stock": self.stock
        }
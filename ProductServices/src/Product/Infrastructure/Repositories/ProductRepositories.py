from src.Database.MySQL import Base, engine, session_local
from src.Product.Domain.Ports.ProductPort import ProductsPort
from src.Product.Domain.Entities.Product import Products
from src.Product.Infrastructure.Models.ModelsMySQL.Products import Products as Model
from src.Product.Infrastructure.DTOS.Response.BaseResponse import BaseResponse


class ProductsRepository(ProductsPort):
    def __init__(self):
        Base.metadata.create_all(bind=engine)
        self.db = session_local()

    def get_products(self):
        productos = [p.to_dict() for p in self.db.query(Model).all()]
        status = True if productos else False
        message = "Productos encontrados exitosamente" if status else "Productos no encontrados"
        status_code = 200 if status else 500
        return self.generate_response(productos, message, status, status_code)

    def create_cant_products(self, products: list[Products]):
        news = [Model(**product.__dict__) for product in products]
        [self.db.add(new) for new in news]
        self.db.commit()
        responses = [n.to_dict() for n in news]
        return self.generate_response(responses, "Productos creados exitosamente", True, 201)

    def delete_products(self, id):
        producto = self.db.query(Model).filter(Model.id == id).first()
        status = True
        status_code = 200
        message = "Producto eliminado con exito"
        if producto:
            self.db.delete(producto)
            self.db.commit()
        else:
            status = False
            message = "Producto no encontrado"
            status_code = 500
        return {"message": message, "status": status}, status_code

    def generate_response(self, data, message, status, httpsstatuscode):
        return BaseResponse(data, message, status, httpsstatuscode).response()
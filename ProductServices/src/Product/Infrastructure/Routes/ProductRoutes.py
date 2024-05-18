from flask import Blueprint, request
from src.Product.Infrastructure.Controllers.ProductsController.GetController import GetController
from src.Product.Infrastructure.Controllers.ProductsController.DeleteController import DeleteController
from src.Product.Infrastructure.Controllers.ProductsController.CreateController import CreateController
from src.Product.Infrastructure.Repositories.ProductRepositories import ProductsRepository

product_routes = Blueprint('product_routes', __name__)

repo = ProductsRepository()
get_controller = GetController(repo)
delete_controller = DeleteController(repo)
create_controller = CreateController(repo)

@product_routes.route("/", methods=["GET"])
def get():
    return get_controller.run()

@product_routes.route("/", methods=["POST"])
def create():
    return create_controller.run(request)

@product_routes.route("/<string:id>", methods=["DELETE"])
def delete(id):
    return delete_controller.run(id)
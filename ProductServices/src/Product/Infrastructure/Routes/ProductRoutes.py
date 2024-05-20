from asyncio import sleep
from threading import Thread
from flask import Blueprint, request
import pika
from src.Product.Infrastructure.Controllers.ProductsController.GetController import GetController
from src.Product.Infrastructure.Controllers.ProductsController.DeleteController import DeleteController
from src.Product.Infrastructure.Controllers.ProductsController.CreateController import CreateController
from src.Product.Infrastructure.Controllers.ProductsController.UpdateCantidadByEvent import UpdateCantidadByEventController
from src.Product.Infrastructure.Repositories.ProductRepositories import ProductsRepository

product_routes = Blueprint('product_routes', __name__)

repo = ProductsRepository()
get_controller = GetController(repo)
delete_controller = DeleteController(repo)
create_controller = CreateController(repo)
updateEvent = UpdateCantidadByEventController(repo)

@product_routes.route("/", methods=["GET"])
def get():
    return get_controller.run()

@product_routes.route("/", methods=["POST"])
def create():
    return create_controller.run(request)

@product_routes.route("/<string:id>", methods=["DELETE"])
def delete(id):
    return delete_controller.run(id)

def callback_queue_change_statusOrden(ch, method, properties, body):
    try:
        updateEvent.run(body)
    except Exception as e:
        print(f"Error processing message: {e}")

def start_consuming_queue_change_statusOrden():
    while True:
        try:
            connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost'))
            channel = connection.channel()
            channel.queue_declare(queue='queue.change.statusOrden', durable=True)
            channel.basic_consume(queue='queue.change.statusOrden', on_message_callback=callback_queue_change_statusOrden, auto_ack=True)
            channel.start_consuming()
        except pika.exception.AMPQConnectionError as e:
            print(f"AMPQ connection error: {e}, retrying in 5 seconds")
            sleep(5)
        except Exception as e:
            print(f"Unexpected error: {e}")
            sleep(5)

def start_consumer_queue_change_statusOrden_thread(  ):
    consumer_thread = Thread(target=start_consuming_queue_change_statusOrden)
    consumer_thread.daemon = True
    consumer_thread.start()
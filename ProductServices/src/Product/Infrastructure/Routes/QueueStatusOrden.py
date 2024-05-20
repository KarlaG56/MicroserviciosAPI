import pika
import json
from threading import Thread

def callback(ch, method, properties, body):
    data = json.loads(body)
    ch.basic_ack(delivery_tag=method.delivery_tag)


def start_consuming():
    connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost'))
    channel = connection.channel()
    channel.queue_declare(queue='queue.change.statusOrden', durable=True)
    channel.basic_consume(queue='queue.change.statusOrden', on_message_callback=callback)
    channel.start_consuming()

def start_consumer_queue_change_orden_status_thread():
    consumer_thread = Thread(target=start_consuming)
    consumer_thread.daemon = True
    consumer_thread.start()
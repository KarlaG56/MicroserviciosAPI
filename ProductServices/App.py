from flask import Flask
from flask_cors import CORS
from src.Product.Infrastructure.Routes.ProductRoutes import product_routes

app = Flask(__name__)
app.register_blueprint(product_routes, url_prefix="/products")
CORS(app)

if __name__ == '__main__':
    app.run(debug=True, port=3001)
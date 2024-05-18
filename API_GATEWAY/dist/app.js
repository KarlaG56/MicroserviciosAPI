"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = __importDefault(require("express"));
const morgan_1 = __importDefault(require("morgan"));
const dotenv_1 = __importDefault(require("dotenv"));
const signale_1 = require("signale");
const express_http_proxy_1 = __importDefault(require("express-http-proxy"));
const app = (0, express_1.default)();
const signale = new signale_1.Signale();
dotenv_1.default.config();
app.use((0, morgan_1.default)('dev'));
const PORT = process.env.PORT || 3000;
const GATEWAY = process.env.SERVICE_NAME;
app.use('/api/v1/orders', (0, express_http_proxy_1.default)('http://localhost:3002'));
app.use('/api/v1/products', (0, express_http_proxy_1.default)('http://localhost:3001'));
// rutas de auth
app.listen(PORT, () => {
    signale.success(`Servicio ${GATEWAY} corriendo en http://localhost:${PORT}`);
});

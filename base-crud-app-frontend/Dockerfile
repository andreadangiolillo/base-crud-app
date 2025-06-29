# Fase 1: build dell'app Angular
FROM node:20-alpine AS build
WORKDIR /app

# Copia package.json e installa dipendenze
COPY package*.json ./
RUN npm install

# Copia tutto il resto e builda l'app
COPY . .
RUN npm run build -- --configuration production

# Fase 2: NGINX per servire l'app
FROM nginx:alpine
COPY --from=build /app/dist/base-crud-app-frontend/browser /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
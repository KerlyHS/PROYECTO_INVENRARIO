import json
from flask import Flask, Blueprint, render_template, request, redirect, url_for, session
import requests

router = Blueprint('router', __name__)

@router.route('/')
def home():
    return render_template('template.html')  # Página de inicio o bienvenida

@router.route('/home')
def homeo():
    return render_template('inicio.html')  # Página de inicio o bienvenida

@router.route('/inventario')
def inventario():
    return render_template('fragmento/inventario.html')  # Ruta de inventario

# Ruta para mostrar el formulario de login
@router.route('/login')
def login():
    return render_template('modulologin/iniciosesion.html')

# Ruta para manejar el envío del formulario de login
@router.route('/iniciosesion', methods=['POST'])
def procesar_login():
    header = {
        "Content-Type": "application/json"
    }
    form = request.form
    data = {
        "correo": form["correo"],
        "clave": form["clave"]
    }

    r = requests.post("http://localhost:8080/myapp/persona/iniciosesion", headers=header, json=data)
    dat = r.json()

    if r.status_code == 200:
        session['token'] = dat["token"]
        session['usuario'] = form["correo"]
        return redirect(url_for('router.dashboard'))  # Redirige al dashboard si login exitoso
    else:
        error = r.json().get("error", "Error al iniciar sesión")
        return render_template('modulologin/iniciosesion.html', error=error)

# Ruta del dashboard
@router.route('/dashboard')
def dashboard():
    if 'token' not in session:
        return redirect(url_for('router.login'))  # Redirige al login si no hay token
    
    return render_template('modulologin/datos.html', usuario=session.get('usuario'))

# Ruta para logout y eliminar la sesión
@router.route('/logout')
def logout():
    session.clear()  # Elimina todos los datos de la sesión
    return redirect(url_for('router.login'))  # Redirige al login

@router.route('/registro' , methods=['POST'])
def registro():
    hearders = {'Content-Type': 'application/json'}
    form = request.form
    
    datar = {
        "nombre": form["nombre"],
        "apellido": form["apellido"],
        "correo": form["correo"],
        "telefono": form["telefono"],
        "dni": form["dni"],
        "clave": form["clave"]	
    }

    r = requests.post("http://localhost:8080/myapp/persona/save", headers=hearders, data=json.dumps(datar))
    dat = r.json()
    if r.status_code == 200:
        if "token" in dat:
            session['token'] = dat["token"]
            session['usuario'] = datar["correo"]
            return redirect(url_for('router.dashboard'))
        else:
            error = dat.get("message", "No se recibió el token en la respuesta del servidor")
            return render_template('modulologin/registro.html', error=error)
    else:
        error = dat.get("message", "Error al registrar")
        return render_template('modulologin/registro.html', error=error)
    
@router.route('/formularegistro')
def formularegistro():
    return render_template('modulologin/registro.html')

# INICIO PRODUCTO

@router.route('/producto/list')
def list_person(msg=''):
    r_producto = requests.get("http://localhost:8080/myapp/producto/list")
    data_producto = r_producto.json()

    print(data_producto)

    return render_template('moduloproducto/producto.html', lista_producto=data_producto["data"])
from flask import Blueprint, render_template

router = Blueprint('router', __name__)

@router.route('/')
def home():
    return render_template('template.html')  # Asegúrate de que esta ruta sea correcta

@router.route('/inventario')
def inventario():
    return render_template('fragmento/inventario.html')  # Ruta dentro de 'templates/fragmento'


# @router.route('/admin/familia/register')
# def view_register_familia():
#     r_familia = requests.get("http://localhost:8086/api/familia/list")
#     data_familia = r_familia.json()
#     r_generador = requests.get("http://localhost:8086/api/generador/list")
#     data_generador = r_generador.json()

#     return render_template('fragmento/familia/registro.html', lista_familia=data_familia["data"],lista_generador=data_generador["data"])

# @router.route('/admin/familia/list')
# def list_person(msg=''):
#     r_familia = requests.get("http://localhost:8086/api/familia/list")
#     data_familia = r_familia.json()
#     r_generador = requests.get("http://localhost:8086/api/generador/list")
#     data_generador = r_generador.json()
#     print(data_familia)
    
#     return render_template('fragmento/familia/lista.html', lista_familia=data_familia["data"],lista_generador=data_generador["data"])

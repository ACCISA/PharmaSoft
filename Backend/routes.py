from flask import Flask, render_template, jsonify, request, session, flash
from functools import wraps
from Database import Initialize
import jwt
import datetime
import uuid
from Employee import Employee
app = Flask(__name__)
app.config['SECRET_KEY'] = 'mysecretkey'

def check_for_token(func):
    @wraps(func)
    def wrapped(*args,**kwargs):
        token = request.args.get('token')
        if not token:
            return jsonify({'message':'token_missing'}), 403

        try:
            data = jwt.decode(token, app.config['SECRET_KEY'],algorithms=['HS256'])
        except:
            return jsonify({'message':'invalid_token','token_recieved':token}), 403
        return func(*args,**kwargs)
    return wrapped
        
@app.route('/')
def index():
    if not session.get('logged_in'):
        return 'go log in'
    else:
        return 'ur logged in'

@app.route('/public')
def public():
    return 'dis public'

@app.route('/auth')
@check_for_token
def auth():
    return 'this is private'

@app.route('/login', methods=['POST'])
def login():
    data = request.get_json()
    password = data['password']
    employee_id = data['employee_id']
    print(password, employee_id)
    if password == None or employee_id == None:
        return jsonify({'message':'information_missing','token':'none'})
    if Employee.verifyLogin(employee_id,password):
        token = jwt.encode({
            'user':str(employee_id),
            'exp': datetime.datetime.utcnow() + datetime.timedelta(seconds=60)
        },
        app.config['SECRET_KEY'])
        return jsonify({'message':'logged_in','token':token})
    return jsonify({'message':'invalid_login','token':'none'})

@app.route('/add_employee', methods=['POST'])
@check_for_token
def addEmployeeRoute():
    data = request.get_json()
    first_name = data['first_name']
    last_name = data['last_name']
    employee_id = data['employee_id']
    password = data['password']
    employee = Employee(first_name, last_name, employee_id,password)
    status = employee.add()
    if not status:
        return jsonify({'message':'false'})
    return jsonify({'message':'true'})

@app.route('/remove_employee')

Initialize()

if __name__ == '__main__':
    app.run(debug=True)
/* *************************************************************
 * Service genérico para permitir chamadas CRUD padrão
 * RESTful. Utilizado para chamar a camada MODEL do Java.
 * 
 * Por: Rafael Lossurdo
 ************************************************************ */
angular.module('CrudServiceModule', [])

.service('CrudService', ['$http', function($http) {
                        
    this.salvar = function(serviceURL, jsonObject, fn) {
        console.log(jsonObject);
        
        $http({
            method: 'POST',
            data: jsonObject,
            url: serviceURL,
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(function successCallback(response) {
            console.log(response);
            fn(response.headers('Location'));
        }, function errorCallback(response) {
            console.log(response);
            geraMensagem("Ooops! " + (response.statusText===""?OOPS:response.statusText));
        });
    }
    
    this.consultar = function(serviceURL, id, fn) {
        console.log(id);
        
        $http.get(serviceURL + "/" + id)
            .then(function successCallback(response) {
                console.log(response);
                fn(response.data);
            }, function errorCallback(response) {
                console.log(response);
                geraMensagem("Ooops! " + (response.statusText===""?OOPS:response.statusText));
            });
    }
    
    this.excluir = function(serviceURL, id, fn) {
        console.log(id);
        
        $http.delete(serviceURL + "/" + id)
            .then(function successCallback(response) {                
                console.log(response);
                fn();
            }, function errorCallback(response) {
                console.log(response);
                geraMensagem("Ooops! " + (response.statusText===""?OOPS:response.statusText));
            });
    }
    
    this.alterar = function(serviceURL, jsonObject, fn) {
        console.log(jsonObject);
        
        $http({
            method: 'PUT',
            data: jsonObject,
            url: serviceURL,
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(function successCallback(response) {
            console.log(response);
            fn(response.headers('Location'));
        }, function errorCallback(response) {
            console.log(response);
            geraMensagem("Ooops! " + (response.statusText===""?OOPS:response.statusText));
        });
    }

    this.pesquisar = function(serviceURL, value, fn) {
        console.log(value);

        $http.get(serviceURL + "?q=" + value)
            .then(function successCallback(response) {
                console.log(response);
                fn(response.data);
            }, function errorCallback(response) {
                console.log(response);
                geraMensagem("Ooops! " + (response.statusText===""?OOPS:response.statusText));
            });
    };
        
    // definição de mensagens padrões para ações de CRUD - abertura do modal
    this.geraMensagemDefault = function() {
        $('#myModalMessage').text("Ação executada com sucesso!");
        $('#myModal').modal();        
    };    

    var geraMensagem = function(mensagem) {
        $('#myModalMessage').text(mensagem);
        $('#myModal').modal();        
    };    

        // http://www.mikezilla.com/exp0012.html
    var OOPS = "\u00AF\\_(\u30C4)_/\u00AF";
        
}]);
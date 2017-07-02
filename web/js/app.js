angular.module('AppModule', ['ngRoute', 'ChamadoModule', 'TecnicoModule', 'ClienteModule', 'UnidadeModule','ngMask'])

.controller('AppController', ['$scope', '$location', function($scope, $location) {
    
    $scope.redir = function (local) {
         $location.path(local);
    };
    
    $scope
}])

.config(['$routeProvider', function ($routeProvider){
        $routeProvider.
 
            when('/chamado-novo', {
                templateUrl: 'partials/chamado-novo.html',
                controller: 'ChamadoController'
        }).
            when('/unidade-novo', {
                templateUrl: 'partials/unidade-novo.html',
                controller: 'UnidadeController'
        }).
            when('/chamado-lista', {
                templateUrl: 'partials/chamado-lista.html',
                controller: 'ChamadoController'
        }).
             when('/tecnico-novo', {
                templateUrl: 'partials/tecnico-novo.html',
                controller: 'TecnicoController'
        }).
            when('/tecnico-lista', {
                templateUrl: 'partials/tecnico-lista.html',
                controller: 'TecnicoController'
        }).
            when('/usuario-novo', {
                templateUrl: 'partials/usuario-novo.html',
                controller: 'ClienteController'
        }).
            when('/usuario-lista', {
                templateUrl: 'partials/usuario-lista.html',
                controller: 'ClienteController'
        }).
            when('/equipamento-novo', {
                templateUrl: 'partials/equipamento-novo.html',
                controller: 'EquipamentoController'
        }).
            when('/', {
                templateUrl: 'partials/saudacao.html',
                controller: 'AppController'
        }).
            when('/sobre-nos', {
                templateUrl: 'partials/sobre-nos.html',
                controller: 'AppController'
        }).
            when('/contatos', {
                templateUrl: 'partials/contatos.html',
                controller: 'AppController'
        }).
            when('/login', {
                templateUrl: 'partials/login.html',
                controller: 'AppController'
        }).
            when('/404', {
                templateUrl: 'partials/404.html'
        }).
        //PÁGINAS NÃO PREVISTAS
            otherwise({
            redirectTo: '/404'
        });
        
}])

;
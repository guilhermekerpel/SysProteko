angular.module('ClienteModule', ['CrudServiceModule'])
        .controller('ClienteController', ['$scope', 'CrudService', '$routeParams', '$http', function ($scope, CrudService, $routeParams, $http) {

                $scope.url = 'http://localhost:8080/SysProtekoWebChamados_FULL/rest/usuarios';

                $scope.objeto = {cliente: ''};
                $scope.clientes = {};
                $scope.editarRegistroCliente = false;

                //criando a combo
                $scope.pesquisar = function () {
                    CrudService.pesquisar($scope.url, $scope.objeto.cliente, function (data) {
                        $scope.resultados = data;
                    });
                };

                //Listei na tela com este método que não é do crud genérico do Lossurdo
                $scope.listar = function () {
                    $http({
                        method: 'GET',
                        url: 'http://localhost:8080/SysProtekoWebChamados_FULL/rest/usuarios'
                    }).then(function (response) {
                        $scope.clientes.lista = response.data;

                        if ($scope.clientes.lista.length > 0) {
                            $scope.existemDados = true;
                        } else {
                            $scope.existemDados = false;
                        }
                    }, function (response) {
                        console.log(response);
                    });
                };

                $scope.consultar = function (id) {
                    CrudService.consultar($scope.url, id, function (obj) {
                        $scope.objeto = obj;
                        $scope.editarRegistroCliente = true;
                    });
                };

                $scope.excluir = function (obj) {
                    CrudService.excluir($scope.url, obj.id, function () {
                        $scope.limpar();
                        CrudService.geraMensagemDefault();
                    });
                };

                $scope.salvar = function () {
                    console.log($scope.objeto);
                    CrudService.salvar($scope.url, $scope.objeto, function (location) {
                        console.log(location);
                        CrudService.geraMensagemDefault();
                        $scope.redir('/usuario-lista');
                    });
                };

                $scope.alterar = function () {
                    CrudService.alterar($scope.url, $scope.objeto, function (location) {
                        console.log(location);
                        CrudService.geraMensagemDefault();
                        $scope.editarRegistroCliente = false;
                    });
                };

                $scope.limpar = function () {
                    $scope.objeto;
                    $scope.clientes = {};
                    $scope.listar();
                };

                $scope.cancelarEdicao = function () {
                    $scope.editarRegistroCliente = false;
                    $scope.limpar();
                };
            }]);
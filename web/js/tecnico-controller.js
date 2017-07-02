angular.module('TecnicoModule', ['CrudServiceModule'])
        .controller('TecnicoController', ['$scope', 'CrudService', '$routeParams', '$http', function ($scope, CrudService, $routeParams, $http) {

                $scope.url = 'http://localhost:8080/SysProtekoWebChamados_FULL/rest/tecnicos';

                $scope.objeto = {tecnico: ''};
                $scope.tecnicos = {};
                $scope.editarRegistroTecnico = false;

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
                        url: 'http://localhost:8080/SysProtekoWebChamados_FULL/rest/tecnicos'
                    }).then(function (response) {
                        $scope.tecnicos.lista = response.data;

                        if ($scope.tecnicos.lista.length > 0) {
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
                        $scope.editarRegistroTecnico = true;
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
                        $scope.redir('/tecnico-lista');
                    });
                };

                $scope.alterar = function () {
                    CrudService.alterar($scope.url, $scope.objeto, function (location) {
                        console.log(location);
                        CrudService.geraMensagemDefault();
                        $scope.editarRegistroTecnico = false;
                    });
                };

                $scope.limpar = function () {
                    $scope.objeto;
                    $scope.tecnicos = {};
                    $scope.listar();
                };

                $scope.cancelarEdicao = function () {
                    $scope.editarRegistroTecnico = false;
                    $scope.limpar();
                };
            }]);
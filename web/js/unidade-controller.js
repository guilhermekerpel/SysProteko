angular.module('UnidadeModule', ['CrudServiceModule']).
        controller('UnidadeController', ['$scope', 'CrudService', function ($scope, CrudService) {

                $scope.url = 'http://localhost:8080/SysProtekoWebChamados_FULL/rest/unidades';
                $scope.objeto = {unidade : ""};
                $scope.unidades = {};
                $scope.resultados;
                
                
                $scope.limpar = function() {
                    $scope.objeto = {unidade:'' };
                    $scope.resultados = null;
                    $scope.consultar();        
                };
                
                $scope.editarRegistroUnidade = false;

                $scope.listar = function () {
                    CrudService.pesquisar($scope.url, $scope.objeto.unidade, function(data) {
                    $scope.resultados = data;
                    });
                };

                $scope.consultar = function (id) {
                     CrudService.consultar($scope.url, id, function(obj) {
                     $scope.objeto = obj;
                    });
                };

                $scope.excluir = function (obj) {
                    CrudService.excluir($scope.url, obj.id, function() {
                    $scope.limpar();
                    CrudService.geraMensagemDefault();
                    });
                };

                $scope.salvar = function () {
                    CrudService.salvar($scope.url, $scope.objeto, function(location) {
                    console.log(location);
                    CrudService.geraMensagemDefault();
                 });
                };

                $scope.alterar = function () {
                    CrudService.alterar($scope.url, $scope.objeto, function(location) {
                    console.log(location);
                    CrudService.geraMensagemDefault();
                    });
                };

                $scope.limpar = function () {
                    $scope.unidade;
                    $scope.unidades = {};
                    $scope.listar();
                };

                $scope.cancelarEdicao = function () {
                    $scope.editarRegistroUnidade = false;
                    $scope.limpar();
                };
                
//                $scope.buscaUnidade = function (id_unidade, obj) {
//                    for(var i=0; i<obj.length; i++) {
//                    if(obj[i].id_unidade === id_unidade) {
//                    return true;
//                 }
//                }
//                return false;
//                };
                
                

            }]);
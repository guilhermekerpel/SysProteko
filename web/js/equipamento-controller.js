angular.module('EquipamentoModule', ['CrudServiceModule'])
        .controller('EquipamentoController', ['$scope', 'CrudService', '$routeParams', '$http', function ($scope, CrudService, $routeParams, $http) {

            $scope.url = 'http://localhost:8080/SysProtekoWebChamados_FULL/rest/equipamentos';
            
            $scope.objeto = {equipamento: ''};
            $scope.equipamentos = {};
            $scope.editarRegistroEquipamento = false;
            
            $scope.pesquisar = function () {
                CrudService.pesquisar($scope.url, $scope.objeto.equipamento, function (data){
                    $scope.resultados = data;
                });
            };
            
            $scope.salvar = function () {
                CrudService.salvar($scope.url, $scope.objeto.equipamento, function (location) {
                    $scope
                })
            }

}]);


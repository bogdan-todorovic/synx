(function() {
    angular.module("synxApp")
        .controller("newReportController", ["$scope", "$rootScope", "$state", "$stateParams", "reportService", function($scope, $rootScope, $state, $stateParams, reportService) {

            $scope.reportMessages = ["This is spam", "This is abusive or harmful", "This contains something private"];
            
            $scope.report = {};
            $scope.report.subforum = $stateParams.subforum;

            $scope.send = function() {
                $scope.report.author = $rootScope.user.username;
                reportService.createReport($scope.report)
                    .then(function(response) {
                        $state.go("home");
                    });
            };
        }]);
})();
(function() {
    angular.module("synxApp")
        .controller("subforumsController", ["$scope", "$rootScope", "subforumService",
            function($scope, $rootScope, subforumService) {

                subforumService.getAllSubforums()
                    .then(function(response) {
                            $scope.subforums = response.data;
                        },
                        function() {
                            $scope.alertMessage = "Unable to load subforums";
                        }
                    );
            }
        ]);
})();
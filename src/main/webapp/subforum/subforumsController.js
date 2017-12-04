(function() {
    angular.module("synxApp")
        .controller("subforumsController", ["$scope", "$rootScope", "fileReaderService", "subforumService",
            function($scope, $rootScope, fileReaderService, subforumService) {

                // Load all subforums
                subforumService.getAllSubforums()
                    .then(function(response) {
                            $scope.subforums = response.data;
                        },
                        function() {
                            $scope.alertMessage = "Unable to load subforums";
                        }
                    );
                
                 // instance for new subforum
                $scope.newSf = {};
                
                // load and preview selected image
                $scope.loadFile = function () {
                    fileReaderService.readAsDataUrl($scope.chosenFile, $scope)
                        .then(function(result) {
                            $scope.newSf.icon = result;
                        });
                };
                
                $scope.create = function() {
                    
                }
            }
        ]);
})();
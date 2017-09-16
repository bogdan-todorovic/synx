(function() {
    angular.module("synxApp")
        .config(["$stateProvider", function($stateProvider) {

            $stateProvider
                .state("subforums", {
                    url: "/subforums",
                    templateUrl: "subforum/subforums.html",
                    controller: "subforumsController"
                });
        }
        ]);
})();


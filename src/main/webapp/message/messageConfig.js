(function() {
    angular.module("synxApp")
        .config(["$stateProvider", function($stateProvider) {

            $stateProvider
                .state("newmessage", {
                    url: "/newmessage/{receiver}",
                    templateUrl: "message/newMessageForm.html",
                    controller: "messageController"
                });
            }
        ]);
})();
    
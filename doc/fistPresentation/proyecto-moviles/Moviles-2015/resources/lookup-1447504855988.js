(function(window, undefined) {
  var dictionary = {
    "2f78b637-828a-4c84-a522-c24fbed85b8d": "CreateAccount",
    "da8ce480-c519-40a5-a488-d63f5cdd9536": "Start",
    "17445edd-cc23-48be-9650-a557138aa920": "DetallePetAdoptar",
    "9c30ebab-691a-49ac-a6f6-149fab1a5c59": "UpdateAccount",
    "6751e826-7dc5-4782-8317-518658be9903": "SearchPet",
    "8ba2b1c9-82c8-4e4c-9fb4-ab548f688f50": "Login",
    "12f95033-51c0-4a0b-bb64-c80c715ca252": "Error screen",
    "1e0eeaf4-d90a-4217-8eab-03dd10002ca9": "GIveAdoptionCandidates",
    "2a7252e2-3b59-4e36-8bc6-213e9f0a9fdf": "Friends",
    "250a20ee-2553-4e08-9f5e-49df240bee62": "AdopptionRequestes",
    "2e94d689-6033-4689-aec2-82acf770824a": "Publicity",
    "5c9535f2-681d-4613-92d9-afc2e142fdc6": "GiveAdoptionPublications",
    "73f64982-33f1-4d42-9456-3fa11d76d2f5": "GiveAdoption",
    "be1f3671-f894-43f4-bfb0-ef497e50d54a": "Reviewers",
    "8114a906-1192-4eed-b0d3-c267ec73b6e5": "CandidateDetail",
    "8a4897e9-b064-4cfa-b08e-6f61f2d8ba02": "Splash screen",
    "e5b07fd3-bc09-4f7c-a23a-d5aeeaf3270a": "Home",
    "dc4ef3dd-65d7-47dc-9ade-9f21ac565f17": "LetPetAdopt",
    "d16e46a7-fdd4-4f62-858f-b0dedcfbaeb4": "AdoptRequestDetail",
    "9ce62e98-b277-4851-9b94-ae38cca10984": "THome",
    "24fc927e-1fcc-4482-8381-9cc506e0a297": "TBlank",
    "f39803f7-df02-4169-93eb-7547fb8c961a": "TBasic"
  };

  var uriRE = /^(\/#)?(screens|templates|masters)\/(.*)(\.html)?/;
  window.lookUpURL = function(fragment) {
    var matches = uriRE.exec(fragment || "") || [],
        folder = matches[2] || "",
        canvas = matches[3] || "",
        name, url;
    if(dictionary.hasOwnProperty(canvas)) { /* search by name */
      url = folder + "/" + canvas;
    }
    return url;
  };

  window.lookUpName = function(fragment) {
    var matches = uriRE.exec(fragment || "") || [],
        folder = matches[2] || "",
        canvas = matches[3] || "",
        name, canvasName;
    if(dictionary.hasOwnProperty(canvas)) { /* search by name */
      canvasName = dictionary[canvas];
    }
    return canvasName;
  };
})(window);
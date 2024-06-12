document.addEventListener("DOMContentLoaded", function () {
      const submit = document.getElementById("sub");
      const title = document.getElementById("title");

      title.addEventListener("keydown", handleKeyDown);

      submit.addEventListener("click", function () {
            if (checkTitle()) return;

            title.value = "";
            return;
      });

      function handleKeyDown(event) {
            if (event.key === "Enter" || event.keyCode === 13) {
                  submit.click();
                  event.preventDefault();
            }
      }

      function checkTitle() {
            const input = title.value;
            if (input.trim().length <= 0) {
                  alert("Task title is empty");
                  return false;
            }
            return true;
      }
});

document
      .querySelector(".divTitle")
      .querySelector("img")
      .addEventListener("click", function (event) {
            event.preventDefault();
            title.value = "";
            document.getElementById("title").focus();
      });

const title = document.querySelector("#title");
const submit = document.querySelector("#sub");
const nextInput = document.querySelector("#next");
const prevInput = document.querySelector("#previous");
const actuInput = document.querySelector("#actu");
const dropChecked = document.querySelector("#dropChecked");
const dropHome = document.querySelector("#dropHome");
const dropTrash = document.querySelector("#dropTrash");

submit.addEventListener("click", function () {
      if (checkTitle()) {
            controlPage({
                  requestController: "addNewTask",
                  titleTask: title.value,
                  method: "POST",
            });
            title.value = "";
            return;
      }
});

nextInput.addEventListener("click", function () {
      controlPage({ requestController: "pageTask", typePage: "next" });
});

prevInput.addEventListener("click", function () {
      controlPage({ requestController: "pageTask", typePage: "previous" });
});

actuInput.addEventListener("click", function () {
      controlPage({ requestController: "pageTask", typePage: "first" });
});

document.addEventListener("DOMContentLoaded", function () {
      titleTooBig();
      bindEvents();
});

function bindEvents() {
      const btnRemoveTasks = document.querySelectorAll(".btnRemoveTasks");
      const btnEditTasks = document.querySelectorAll(".btnEditTasks");
      const chIsCloses = document.querySelectorAll(".chIsClose");

      withRedirect(btnEditTasks, "updateTaskPage");
      withNoRedirect(btnRemoveTasks, "removeTask");
      withNoRedirect(chIsCloses, "changeIsClose");

      function withRedirect(elements, requestController) {
            elements.forEach((element) => {
                  const taskElement = element.closest(".taskP");
                  const taskId = taskElement ? taskElement.id : null;
                  if (taskId) {
                        element.addEventListener("click", function () {
                              let url = `/${requestController}/${taskId}/${taskElement.querySelector(".chIsClose")
                                          .checked
                                    }`;
                              window.location.href = url;
                        });
                  } else {
                        console.warn("Task ID not found for button:", element);
                  }
            });
      }

      function withNoRedirect(elements, requestController) {
            elements.forEach((element) => {
                  const taskElement = element.closest(".taskP");
                  const taskId = taskElement ? taskElement.id : null;
                  if (taskId) {
                        element.addEventListener("click", function () {
                              controlPage({
                                    requestController: requestController,
                                    arquiveName: "body",
                                    fragmentGiver: "tasks",
                                    fragmentReceiver: "tasks",
                                    id: taskId,
                              });
                        });
                  } else {
                        console.warn("Task ID not found for button:", element);
                        `window.location.href='/error'`;
                  }
            });
      }
}

title.addEventListener("keydown", handleKeyDown);

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

function titleTooBig() {
      const tooltips = document.querySelectorAll(".eachTask");
      tooltips.forEach((tooltip) => {
            tooltipValue = tooltip.textContent;
            if (tooltipValue.length > 20)
                  tooltip.textContent = tooltipValue.substr(0, 20) + "...";
      });
}

function redirect(param) {
      window.location.href = param;
}

document
      .querySelector(".divTitle")
      .querySelector("img")
      .addEventListener("click", function (event) {
            event.preventDefault();
            title.value = "";
            document.getElementById("title").focus();
      });

const nextInput = document.querySelector("#next");
const prevInput = document.querySelector("#previous");
const actuInput = document.querySelector("#actu");
const dropChecked = document.querySelector("#dropChecked");
const dropHome = document.querySelector("#dropHome");
const dropTrash = document.querySelector("#dropTrash");

nextInput.addEventListener("click", function () {
      controlPage({
            requestController: "pageTrash",
            fragmentGiver: "trashs",
            fragmentReceiver: "trashs",
            typePage: "next",
      });
});

prevInput.addEventListener("click", function () {
      controlPage({
            requestController: "pageTrash",
            fragmentGiver: "trashs",
            fragmentReceiver: "trashs",
            typePage: "previous",
      });
});

actuInput.addEventListener("click", function () {
      controlPage({
            requestController: "page",
            requestController: "pageTrash",
            fragmentGiver: "trashs",
            fragmentReceiver: "trashs",
            typePage: "first",
      });
});

function bindEvents() {
      const btnRemoveTasks = document.querySelectorAll(".btnRemoveTasks");
      const btnRestoreTasks = document.querySelectorAll(".btnRestoreTasks");

      withNoRedirect(btnRemoveTasks, "removeTrash");
      withNoRedirect(btnRestoreTasks, "restoreTrash");

      function withNoRedirect(elements, requestController) {
            elements.forEach((element) => {
                  const taskElement = element.closest(".taskP");
                  const taskId = taskElement ? taskElement.id : null;
                  if (taskId) {
                        element.addEventListener("click", function () {
                              controlPage({
                                    requestController: requestController,
                                    arquiveName: "body",
                                    fragmentGiver: "trashs",
                                    fragmentReceiver: "trashs",
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

document.addEventListener("DOMContentLoaded", function () {
      titleTooBig();
      bindEvents();
});

function redirect(param) {
      window.location.href = param;
}

function titleTooBig() {
      const tooltips = document.querySelectorAll(".eachTask");
      tooltips.forEach((tooltip) => {
            tooltipValue = tooltip.textContent;
            if (tooltipValue.length > 20)
                  tooltip.textContent = tooltipValue.substr(0, 20) + "...";
      });
}

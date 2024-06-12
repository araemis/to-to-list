async function controlPage({
      requestController, //link
      pathToFragment = "taskFragments",
      arquiveName = "body",
      fragmentGiver = "tasks",
      fragmentReceiver = "tasks",
      typePage,
      id,
      titleTask,
      method = "GET",
} = {}) {
      const urlParams = [
            requestController,
            pathToFragment,
            arquiveName,
            fragmentGiver,
            typePage,
            id,
      ];

      let url = "";

      urlParams.forEach((urlParam) => {
            if (urlParam) url += `${urlParam}/`;
      });

      url = url.slice(0, -1);

      console.log(url);

      await fetch(url, {
            method: method, // *GET, POST, PUT, DELETE, etc.
            body: titleTask,
      })
            .then((response) => response.text())
            .then((html) => {
                  const res = document.getElementById(fragmentReceiver);
                  res.innerHTML = html;

                  let helpValues = document.querySelector("[name=helpValues]");

                  title.focus();

                  actuInput.value = helpValues.getAttribute("page");

                  titleTooBig();
                  bindEvents();
            })
            .catch((err) => {
                  console.warn("Something went wrong.", err);
            });
}

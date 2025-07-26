
import { render, screen } from "@testing-library/react";
import { BrowserRouter } from "react-router-dom";
import App from "./App";
import { act } from "react-dom/test-utils";

test("renders workspace list", async() => {
  const fakeWorkspace = [
    {
      id: 2,
      workSpaceName: "my home workspace",
      workSpaceDescription: "home workspace",
      workSpaceType: "business",
    },
  ];
  jest.spyOn(global, 'fetch').mockImplementation(() => {
    return Promise.resolve({
      json: () => Promise.resolve(fakeWorkspace)
    })
  })
  await act(() => {
    render(
      <BrowserRouter>
        <App />
      </BrowserRouter>
    );
  });
  const domTag = screen.getAllByText(fakeWorkspace[0].workSpaceName)[0];
  expect(domTag).toBeInTheDocument();
  // const linkElement = screen.getByText(/learn react/i);
  // expect(linkElement).toBeInTheDocument();
});

test("Click add icon to show create form", async () => {
  const fakeWorkspace = [];
  jest.spyOn(global, 'fetch').mockImplementation(() => {
    return Promise.resolve({
      json: () => Promise.resolve(fakeWorkspace)
    })
  })
  await act(() => {
    render(
      <BrowserRouter>
        <App />
      </BrowserRouter>
    );
  });
  const button = document.querySelector("[data-testid=create]");
  await act(() => {
    button.dispatchEvent(new MouseEvent('click', {bubbles: true}));
  })
  const startWorkTextDom = screen.getByText("Start your works!");
  expect(startWorkTextDom).toBeInTheDocument();
});


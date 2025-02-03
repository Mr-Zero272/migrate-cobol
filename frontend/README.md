### <h2 align="center">API DOCUMENT FOR FRONTEND</h2>

## I. Components (`src/components`)

### 1. Button

The `Button` component is a versatile button component that can be used as a standard button or a navigation link (`NavLink`). It supports various sizes, styles, and optional icons. It is designed to be easily customizable and adaptable to different use cases within a React application.

#### Usage

##### Props

- **Common Props**
  - `size`: Specifies the size of the button. Possible values are 'small', 'medium', 'large', or 'block'
  - `icon`: (Optional) Specifies the FontAwesome icon to be displayed.
  - `iconTextSpace`: (Optional) Specifies the spacing between the icon and text. Possible values are 'small', 'medium', or 'large'.
  - `isActive`: (Optional) Sets the button/link as active.
  - `classes`: (Optional) Additional CSS classes to be applied to the button/link.
- **Button Props**
  - `text`: (Optional) Specifies the text content of the button.
  - `noBackground`: (Optional) If 'true', removes the background color of the button.
- **Anchor Props**
  - `to`: Specifies the target URL for navigation.
  - `text`: Specifies the text content of the link.

##### Example

```
  <Button
    size="medium"
    icon={/* FontAwesome icon definition */}
    iconTextSpace="medium"
    isActive={true}
    classes="custom-class"
    onClick={/* Handle button click */}
    text="Click me"
  />

  <Button
    size="large"
    noBackground={true}
    onClick={/* Handle button click */}
    text="Text only button"
  />

  <Button
    to="/dashboard"
    size="small"
    icon={/* FontAwesome icon definition */}
    iconTextSpace="small"
    isActive={true}
    classes="custom-class"
    text="Navigate to Dashboard"
  />
```

#### Styling

The styling for the Button component is implemented using CSS modules. Styles are imported from the `Button.module.css` file.

### 2. Input

The `Input` component is a customizable input field that supports additional features such as setting maximum length and custom styling.

#### Usage

##### Props

- `maxLength`: (Optional) Specifies the maximum number of characters allowed in the input field.
- `classes`: (Optional) Additional CSS classes to be applied to the input field.

##### Example

```
<Input maxLength={20} classes="custom-class" />
```

#### Styling

The styling for the `Input` component is implemented using inline styles and a dynamic width based on the `maxLength` prop. The class `input-${theme.name}` is applied to the input field, where `theme.name` is obtained from the Redux store.

#### Notes

The component relies on the `theme` obtained from the Redux store for dynamic styling. Ensure Redux is properly set up in your project and the `theme` slice is available in the store.

### 3. Menu

#### 3.1. Menu

The `Menu` component is a versatile menu component that supports nested items, back navigation, and interactive features. It utilizes Tippy for tooltips and FontAwesome for icons.

#### Usage

##### Props

- `items`: An array of MenuItemProps representing the menu items.
- `onClick`: A callback function triggered when a menu item is clicked.
- `hideOnClick`: (Optional) If true, the menu will hide when clicked.
- `placement`: (Optional) Specifies the placement of the menu. - Default is 'bottom-start'.
- Additional TippyProps can also be passed.

##### Example

```
<Menu
  items={[
    { text: 'Home', to: '/' },
    { text: 'Products', nestedItems: [{ text: 'Product 1' }, { text: 'Product 2' }] },
    { text: 'Contact', to: '/contact' },
  ]}
  onClick={(item) => console.log(`Clicked on ${item.text}`)}
>
  {/* Trigger element, e.g., a button */}
  <button>Open Menu</button>
</Menu>
```

#### 3.2. MenuHeader

The `MenuHeader` component represents the header of the menu and provides a back button for navigating through nested items.

#### Usage

##### Props

- `title`: The title to be displayed in the header.
- `onBack`: A callback function triggered when the back button is clicked.

#### 3.3. MenuItem

The `MenuItem` component represents an individual menu item. It supports both regular buttons and links for navigation.

#### Usage

##### Props

- `text`: The text content of the menu item.
- `to`: (Optional) Specifies the target URL for navigation.
- `type`: (Optional) Additional type information for the menu item.
- `code`: (Optional) Additional code information for the menu item.
- `nestedItems`: (Optional) An array of MenuItemProps representing nested items.
- `icon`: (Optional) Specifies the FontAwesome icon for the menu item.
- `onClick`: (Optional) A callback function triggered when the menu item is clicked.

#### 3.4. MenuWrapper

The `MenuWrapper` component is a simple wrapper that applies additional styling to the menu content.

#### Usage

##### Props

- `classes`: Additional CSS classes to be applied to the wrapper.
- `children`: The content of the menu.

##### Styling

The styling for the menu components is implemented using CSS modules. Styles are imported from the Menu.module.css file.

### 4. GridSystem

#### 4.1. GridSystem

The `GridSystem` component consists of `GridMatrix` and `GridItem` components, providing a flexible grid layout system for organizing content.

#### Usage

The `GridMatrix` component serves as the container for the grid layout.

##### Props

- `children`: The content to be placed within the grid matrix.

##### Example

```
  <GridMatrix>
    {/* GridItem components go here */}
  </GridMatrix>
```

#### 4.2. GridItem

The `GridItem` component represents an individual grid item within the matrix.

#### Usage

##### Props

- `row`: The starting row position of the grid item.
- `rowEnd`: (Optional) The ending row position of the grid item.
- `col`: The starting column position of the grid item.
- `colEnd`: (Optional) The ending column position of the grid item.
- `lineBreak`: (Optional) If `true`, applies a line break styling based on the theme.
- `classes`: (Optional) Additional CSS classes to be applied to the grid item.
- `children`: The content to be placed within the grid item.

##### Example

```
  <GridItem row={1} col={1} rowEnd={2} colEnd={3}>
    {/* Content of the grid item */}
  </GridItem>
```

### 5. GlobalStyles

The `GlobalStyles` component is a simple wrapper component that allows you to include global styles for your application.

#### Usage

##### Props

- `children`: The content of the component.

##### Example

Wrap your application or a specific section with the GlobalStyles component to apply global styles:

```
  <GlobalStyles>
    {/* Your application or component content goes here */}
  </GlobalStyles>
```

### II. Config (`src/config`)

### 1. httpConfig

Specifying the domain and various endpoints/resources for user-related functionalities and authentication.

#### Usage

You can import and use this configuration object in your application to make API requests. Here's an example of how you might use it with a JavaScript/TypeScript application:

```
import apiConfig from 'path/to/apiConfig';

// Example API request
const userEndpoint = `${apiConfig.domain}${apiConfig.resources.user}`;

fetch(userEndpoint)
  .then((response) => response.json())
  .then((data) => console.log(data))
  .catch((error) => console.error('Error fetching user data:', error));
```

#### Configuration Object

- `domain`: The base URL of the API, such as `http://localhost:8080`.
- `resources`: An object defining various API `endpoints/resources`.
- `user`: The endpoint for user-related resources, such as `/api/v1/user`.
- `auth`: An object defining authentication-related endpoints.
- `signUp`: The endpoint for user registration, such as `/api/v1/auth/signup`.
- `signIn`: The endpoint for user login, such as `/api/v1/auth/signin`.
- `refreshToken`: The endpoint for refreshing authentication tokens, such as `/api/v1/auth/refresh`.

### 2. routeConfig

Defines a configuration object for routes in a application. It includes base routes, authentication routesand user management routes. Additionally, it routes from `dspfRoutes` and `bmsRoutes`.

#### Base routes

- `home`: The home route, usually `'/'`.
- `login`: The route for authentication or login, typically `'/login'`.
- `dashboard`: The main dashboard route, often `'/admin'`.
- User management routes:
  - `userDetails`: Route to view user details (`'/admin/user/:username'`).
  - `userCreate`: Route to create a new user (`'/admin/user/create'`).
  - `userUpdate`: Route to update user information (`'/admin/user/update/:username'`).
  - `userChangePassword`: Route to change a user's password (`'/admin/user/password/:username'`).
  - `userDelete`: Route to delete a user (`'/admin/user/delete/:username'`).

#### Additional Routes

The `bmsRoutesConfig` and `dspfRoutesConfig` include routes from `bmsRoutes` and `dspfRoutes` respectively. The routes are mapped to an object structure for further configuration.

#### Merged Configuration

The final configuration object includes all base routes, `bmsRoutes`, and `dspfRoutes`. The object is exported for use in the application.

#### Example

```
import routesConfig from 'path/to/routesConfig';

// Accessing a route
const homeRoute = routesConfig.home; // '/'
const userDetailsRoute = routesConfig.userDetails; // '/admin/user/:username'

// Iterate over all routes
Object.values(routesConfig).forEach((route) => {
  console.log(route);
});
```

## 6. features (`src/features`)

### Redux store (`store.tsx`)

Manages the state of themes, refresh tokens, and regular tokens. Additionally, it incorporates the `redux-persist` library for persisting state across application reloads.

#### Configuration

##### Persist configuration

The `persistConfig` object is responsible for configuring state persistence using `redux-persist`. It specifies the key as `'root'` and utilizes `redux-persist/lib/storage` for storage.

##### Root reducer

The `persistedReducer` applies the `persistReducer` to the `rootReducer` with the configuration specified in `persistConfig`.

##### Redux store creation

The `store` is created using `configureStore` from `redux-toolkit`. It uses the `persistedReducer` as the main reducer and configures middleware to handle serialization checks. Additionally, it ignores specific actions during serialization checks.

##### Persistor

The `persistor` is created using `persistStore` from `redux-persist`. It is utilized to persist the Redux store, ensuring that the state is maintained across page reloads.

##### Types

- `RootState`: Represents the inferred type of the complete Redux state.
- `AppDispatch`: Represents the inferred type of the dispatch function.

### themesSlices

The `themesSlice` module is a Redux reducer responsible for managing the theme state in your application. It employs the `createSlice` utility from `redux-toolkit` to streamline the process of creating actions and reducers. This documentation provides insights into the structure and usage of the `themesSlice` module.

### refreshTokenSlices

The `refreshTokenSlices` module is a Redux slice responsible for managing the state of a refresh token in your application. It includes actions such as `refreshToken`, `setRefreshToken`, and `setExpiration`. This documentation provides an overview of the structure, actions, and usage of the `refreshTokenSlices` module.

### tokenSlices

The initial state of the `tokenSlices` module is defined as a `Token` object with properties `token` and `expiration`. The initial values are an empty string for the token and `0` for expiration.

## 7. layouts

### DefaultLayout

The `DefaultLayout` component serves as a consistent and organized layout for your application, promoting reusability and maintainability. It leverages various components to structure the user interface, including a header, footer, sidebar, main content area, and additional interactive elements such as a toolbar and theme selection menu.

#### Component Structure

##### Header

The `Header` component is included at the top of the layout, providing a consistent header for your application.

##### Footer

The `Footer` component is included at the bottom of the layout, offering a standardized footer section.

##### Sidebar

The `Sidebar` component is positioned on the left side of the layout, providing a navigation menu or other relevant content.

##### MainContent

The `MainContent` component represents the main area of your application, where the primary content is displayed.

##### Toolbar

The `Toolbar` component is included in the main content area, offering additional tools or actions.

##### Button

The `Button` component is a customizable button used in various places within the layout, such as the toolbar.

##### Menu

The `Menu` component is an interactive menu with dropdown functionality. In this layout, it is used for theme selection.

##### Usage Example

```
import DefaultLayout from 'path/to/DefaultLayout';

const App = () => {
  return (
    <DefaultLayout>
      {/* Your application content goes here */}
    </DefaultLayout>
  );
};
```

## 8. pages (`src/pages`)

The `pages` folder in your project contains generated pages from BMS (Basic Mapping Support) and DSPF (Display File) files. These pages are dynamically generated by a Python tool. This documentation provides an overview of the structure and purpose of the `pages` folder.

### Generated BMS Pages

The `BMSPage` subfolder contains pages that were generated from BMS files. BMS is a file format used in mainframe systems for defining the layout and structure of screens. The generated pages in this folder likely represent UI components or screens in the application.

### Generated DSPF Pages

The `DSPFPage` subfolder contains pages that were generated from DSPF files. DSPF is another file format used in mainframe systems for defining display file screens. Similar to BMS, the generated pages in this folder represent UI components or screens in your application.

## 9. routes

The `routeConfig` file in the project is responsible for configuring and defining routes for the application. It includes both public and private routes, each associated with a specific component and layout.

#### Public routes

Public routes are accessible to all users, and they include routes for pages like the home page and login page. BMSPage and DSPFPage routes are also added dynamically from the corresponding generated routes.

#### Private routes

Private routes are accessible only to authenticated users. These routes include the dashboard and various user management routes.

#### Example

```
// Import the route configuration
import { publicRoute, privateRoutes } from 'path/to/routeConfig';

// Example usage in a React component (using React Router)
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

const App = () => {
  return (
    <Router>
      <Switch>
        {/* Public Routes */}
        {publicRoute.map((route) => (
          <Route
            key={route.path}
            exact
            path={route.path}
            component={route.layout(route.component)}
          />
        ))}
        {/* Private Routes */}
        {privateRoutes.map((route) => (
          <Route
            key={route.path}
            exact
            path={route.path}
            component={route.layout(route.component)}
          />
        ))}
      </Switch>
    </Router>
  );
};
```

## 10. utils

### dateTimeFormat

These utility functions, `dateFormat` and `timeFormat`, provide a convenient way to format `Date` objects into human-readable date and time strings. These formatted strings can be used in various parts of your application, such as displaying timestamps or other time-related information.

#### Function

##### dateFormat

The `dateFormat` function formats a `Date` object into a string with the format "YY/MM/DD", representing the year, month, and day.

##### timeFormat

The `timeFormat` function formats a `Date` object into a string with the format "HH:mm:ss", representing the hours, minutes, and seconds.

import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ImmunizationItems from './immunization-items';
import ImmunizationItemsDetail from './immunization-items-detail';
import ImmunizationItemsUpdate from './immunization-items-update';
import ImmunizationItemsDeleteDialog from './immunization-items-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ImmunizationItemsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ImmunizationItemsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ImmunizationItemsDetail} />
      <ErrorBoundaryRoute path={match.url} component={ImmunizationItems} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ImmunizationItemsDeleteDialog} />
  </>
);

export default Routes;

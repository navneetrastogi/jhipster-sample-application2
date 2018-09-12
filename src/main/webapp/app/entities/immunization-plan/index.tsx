import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ImmunizationPlan from './immunization-plan';
import ImmunizationPlanDetail from './immunization-plan-detail';
import ImmunizationPlanUpdate from './immunization-plan-update';
import ImmunizationPlanDeleteDialog from './immunization-plan-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ImmunizationPlanUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ImmunizationPlanUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ImmunizationPlanDetail} />
      <ErrorBoundaryRoute path={match.url} component={ImmunizationPlan} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ImmunizationPlanDeleteDialog} />
  </>
);

export default Routes;

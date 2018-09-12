import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import IncidentRecord from './incident-record';
import IncidentRecordDetail from './incident-record-detail';
import IncidentRecordUpdate from './incident-record-update';
import IncidentRecordDeleteDialog from './incident-record-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={IncidentRecordUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={IncidentRecordUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={IncidentRecordDetail} />
      <ErrorBoundaryRoute path={match.url} component={IncidentRecord} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={IncidentRecordDeleteDialog} />
  </>
);

export default Routes;

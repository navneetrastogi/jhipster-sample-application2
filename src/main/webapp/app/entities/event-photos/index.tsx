import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EventPhotos from './event-photos';
import EventPhotosDetail from './event-photos-detail';
import EventPhotosUpdate from './event-photos-update';
import EventPhotosDeleteDialog from './event-photos-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EventPhotosUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EventPhotosUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EventPhotosDetail} />
      <ErrorBoundaryRoute path={match.url} component={EventPhotos} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EventPhotosDeleteDialog} />
  </>
);

export default Routes;

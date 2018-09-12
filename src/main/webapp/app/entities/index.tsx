import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Organization from './organization';
import Center from './center';
import Room from './room';
import Teacher from './teacher';
import Task from './task';
import TaskType from './task-type';
import Student from './student';
import StudentProfile from './student-profile';
import Schedule from './schedule';
import ScheduleItem from './schedule-item';
import Instruction from './instruction';
import Notification from './notification';
import Attendance from './attendance';
import Incident from './incident';
import IncidentRecord from './incident-record';
import Event from './event';
import EventPhotos from './event-photos';
import ImmunizationRecord from './immunization-record';
import ImmunizationPlan from './immunization-plan';
import ImmunizationItems from './immunization-items';
import IllnessRecord from './illness-record';
import Kudos from './kudos';
import KudosRecord from './kudos-record';
import Milestone from './milestone';
import MilestoneRecord from './milestone-record';
import Holiday from './holiday';
import Timeline from './timeline';
import Activity from './activity';
import ActivityType from './activity-type';
import Media from './media';
import Conversation from './conversation';
import Payment from './payment';
import Parent from './parent';
import Permission from './permission';
import Feature from './feature';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/organization`} component={Organization} />
      <ErrorBoundaryRoute path={`${match.url}/center`} component={Center} />
      <ErrorBoundaryRoute path={`${match.url}/room`} component={Room} />
      <ErrorBoundaryRoute path={`${match.url}/teacher`} component={Teacher} />
      <ErrorBoundaryRoute path={`${match.url}/task`} component={Task} />
      <ErrorBoundaryRoute path={`${match.url}/task-type`} component={TaskType} />
      <ErrorBoundaryRoute path={`${match.url}/student`} component={Student} />
      <ErrorBoundaryRoute path={`${match.url}/student-profile`} component={StudentProfile} />
      <ErrorBoundaryRoute path={`${match.url}/schedule`} component={Schedule} />
      <ErrorBoundaryRoute path={`${match.url}/schedule-item`} component={ScheduleItem} />
      <ErrorBoundaryRoute path={`${match.url}/instruction`} component={Instruction} />
      <ErrorBoundaryRoute path={`${match.url}/notification`} component={Notification} />
      <ErrorBoundaryRoute path={`${match.url}/attendance`} component={Attendance} />
      <ErrorBoundaryRoute path={`${match.url}/incident`} component={Incident} />
      <ErrorBoundaryRoute path={`${match.url}/incident-record`} component={IncidentRecord} />
      <ErrorBoundaryRoute path={`${match.url}/event`} component={Event} />
      <ErrorBoundaryRoute path={`${match.url}/event-photos`} component={EventPhotos} />
      <ErrorBoundaryRoute path={`${match.url}/immunization-record`} component={ImmunizationRecord} />
      <ErrorBoundaryRoute path={`${match.url}/immunization-plan`} component={ImmunizationPlan} />
      <ErrorBoundaryRoute path={`${match.url}/immunization-items`} component={ImmunizationItems} />
      <ErrorBoundaryRoute path={`${match.url}/illness-record`} component={IllnessRecord} />
      <ErrorBoundaryRoute path={`${match.url}/kudos`} component={Kudos} />
      <ErrorBoundaryRoute path={`${match.url}/kudos-record`} component={KudosRecord} />
      <ErrorBoundaryRoute path={`${match.url}/milestone`} component={Milestone} />
      <ErrorBoundaryRoute path={`${match.url}/milestone-record`} component={MilestoneRecord} />
      <ErrorBoundaryRoute path={`${match.url}/holiday`} component={Holiday} />
      <ErrorBoundaryRoute path={`${match.url}/timeline`} component={Timeline} />
      <ErrorBoundaryRoute path={`${match.url}/activity`} component={Activity} />
      <ErrorBoundaryRoute path={`${match.url}/activity-type`} component={ActivityType} />
      <ErrorBoundaryRoute path={`${match.url}/media`} component={Media} />
      <ErrorBoundaryRoute path={`${match.url}/conversation`} component={Conversation} />
      <ErrorBoundaryRoute path={`${match.url}/payment`} component={Payment} />
      <ErrorBoundaryRoute path={`${match.url}/parent`} component={Parent} />
      <ErrorBoundaryRoute path={`${match.url}/permission`} component={Permission} />
      <ErrorBoundaryRoute path={`${match.url}/feature`} component={Feature} />
      {/* jhipster-needle-add-route-path - JHipster will routes here */}
    </Switch>
  </div>
);

export default Routes;

import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './schedule-item.reducer';
import { IScheduleItem } from 'app/shared/model/schedule-item.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IScheduleItemUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IScheduleItemUpdateState {
  isNew: boolean;
}

export class ScheduleItemUpdate extends React.Component<IScheduleItemUpdateProps, IScheduleItemUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { scheduleItemEntity } = this.props;
      const entity = {
        ...scheduleItemEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
      this.handleClose();
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/schedule-item');
  };

  render() {
    const { scheduleItemEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplication2App.scheduleItem.home.createOrEditLabel">Create or edit a ScheduleItem</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : scheduleItemEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="schedule-item-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="isDoneLabel" check>
                    <AvInput id="schedule-item-isDone" type="checkbox" className="form-control" name="isDone" />
                    Is Done
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="itemTypeLabel">Item Type</Label>
                  <AvInput
                    id="schedule-item-itemType"
                    type="select"
                    className="form-control"
                    name="itemType"
                    value={(!isNew && scheduleItemEntity.itemType) || 'EVENT'}
                  >
                    <option value="EVENT">EVENT</option>
                    <option value="ACTIVITY">ACTIVITY</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="titleLabel" for="title">
                    Title
                  </Label>
                  <AvField id="schedule-item-title" type="text" name="title" />
                </AvGroup>
                <AvGroup>
                  <Label id="descriptionLabel" for="description">
                    Description
                  </Label>
                  <AvField id="schedule-item-description" type="text" name="description" />
                </AvGroup>
                <AvGroup>
                  <Label id="scheduledDateLabel" for="scheduledDate">
                    Scheduled Date
                  </Label>
                  <AvField id="schedule-item-scheduledDate" type="date" className="form-control" name="scheduledDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="startTimeLabel" for="startTime">
                    Start Time
                  </Label>
                  <AvField id="schedule-item-startTime" type="date" className="form-control" name="startTime" />
                </AvGroup>
                <AvGroup>
                  <Label id="endtTimeLabel" for="endtTime">
                    Endt Time
                  </Label>
                  <AvField id="schedule-item-endtTime" type="date" className="form-control" name="endtTime" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/schedule-item" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  scheduleItemEntity: storeState.scheduleItem.entity,
  loading: storeState.scheduleItem.loading,
  updating: storeState.scheduleItem.updating
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ScheduleItemUpdate);

import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './attendance.reducer';
import { IAttendance } from 'app/shared/model/attendance.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAttendanceUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IAttendanceUpdateState {
  isNew: boolean;
}

export class AttendanceUpdate extends React.Component<IAttendanceUpdateProps, IAttendanceUpdateState> {
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
      const { attendanceEntity } = this.props;
      const entity = {
        ...attendanceEntity,
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
    this.props.history.push('/entity/attendance');
  };

  render() {
    const { attendanceEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplication2App.attendance.home.createOrEditLabel">Create or edit a Attendance</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : attendanceEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="attendance-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="checkinLabel" for="checkin">
                    Checkin
                  </Label>
                  <AvField id="attendance-checkin" type="date" className="form-control" name="checkin" />
                </AvGroup>
                <AvGroup>
                  <Label id="checkoutLabel" for="checkout">
                    Checkout
                  </Label>
                  <AvField id="attendance-checkout" type="date" className="form-control" name="checkout" />
                </AvGroup>
                <AvGroup>
                  <Label id="datetimeLabel" for="datetime">
                    Datetime
                  </Label>
                  <AvField id="attendance-datetime" type="date" className="form-control" name="datetime" />
                </AvGroup>
                <AvGroup>
                  <Label id="createdOnLabel" for="createdOn">
                    Created On
                  </Label>
                  <AvField id="attendance-createdOn" type="date" className="form-control" name="createdOn" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/attendance" replace color="info">
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
  attendanceEntity: storeState.attendance.entity,
  loading: storeState.attendance.loading,
  updating: storeState.attendance.updating
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
)(AttendanceUpdate);

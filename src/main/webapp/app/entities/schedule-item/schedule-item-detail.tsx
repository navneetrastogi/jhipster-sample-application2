import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './schedule-item.reducer';
import { IScheduleItem } from 'app/shared/model/schedule-item.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IScheduleItemDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class ScheduleItemDetail extends React.Component<IScheduleItemDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { scheduleItemEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            ScheduleItem [<b>{scheduleItemEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="isDone">Is Done</span>
            </dt>
            <dd>{scheduleItemEntity.isDone ? 'true' : 'false'}</dd>
            <dt>
              <span id="itemType">Item Type</span>
            </dt>
            <dd>{scheduleItemEntity.itemType}</dd>
            <dt>
              <span id="title">Title</span>
            </dt>
            <dd>{scheduleItemEntity.title}</dd>
            <dt>
              <span id="description">Description</span>
            </dt>
            <dd>{scheduleItemEntity.description}</dd>
            <dt>
              <span id="scheduledDate">Scheduled Date</span>
            </dt>
            <dd>
              <TextFormat value={scheduleItemEntity.scheduledDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="startTime">Start Time</span>
            </dt>
            <dd>
              <TextFormat value={scheduleItemEntity.startTime} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="endtTime">Endt Time</span>
            </dt>
            <dd>
              <TextFormat value={scheduleItemEntity.endtTime} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
          </dl>
          <Button tag={Link} to="/entity/schedule-item" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/schedule-item/${scheduleItemEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ scheduleItem }: IRootState) => ({
  scheduleItemEntity: scheduleItem.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ScheduleItemDetail);

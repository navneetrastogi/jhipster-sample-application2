import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './schedule-item.reducer';
import { IScheduleItem } from 'app/shared/model/schedule-item.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IScheduleItemProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class ScheduleItem extends React.Component<IScheduleItemProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { scheduleItemList, match } = this.props;
    return (
      <div>
        <h2 id="schedule-item-heading">
          Schedule Items
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Schedule Item
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Is Done</th>
                <th>Item Type</th>
                <th>Title</th>
                <th>Description</th>
                <th>Scheduled Date</th>
                <th>Start Time</th>
                <th>Endt Time</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {scheduleItemList.map((scheduleItem, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${scheduleItem.id}`} color="link" size="sm">
                      {scheduleItem.id}
                    </Button>
                  </td>
                  <td>{scheduleItem.isDone ? 'true' : 'false'}</td>
                  <td>{scheduleItem.itemType}</td>
                  <td>{scheduleItem.title}</td>
                  <td>{scheduleItem.description}</td>
                  <td>
                    <TextFormat type="date" value={scheduleItem.scheduledDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={scheduleItem.startTime} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={scheduleItem.endtTime} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${scheduleItem.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${scheduleItem.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${scheduleItem.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ scheduleItem }: IRootState) => ({
  scheduleItemList: scheduleItem.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ScheduleItem);

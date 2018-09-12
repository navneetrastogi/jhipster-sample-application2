import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './instruction.reducer';
import { IInstruction } from 'app/shared/model/instruction.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IInstructionProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Instruction extends React.Component<IInstructionProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { instructionList, match } = this.props;
    return (
      <div>
        <h2 id="instruction-heading">
          Instructions
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Instruction
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Schedule Item Id</th>
                <th>Video URL</th>
                <th>Photo UR Ls</th>
                <th>Instruction</th>
                <th>Title</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {instructionList.map((instruction, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${instruction.id}`} color="link" size="sm">
                      {instruction.id}
                    </Button>
                  </td>
                  <td>{instruction.scheduleItemId}</td>
                  <td>{instruction.videoURL}</td>
                  <td>{instruction.photoURLs}</td>
                  <td>{instruction.instruction}</td>
                  <td>{instruction.title}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${instruction.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${instruction.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${instruction.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ instruction }: IRootState) => ({
  instructionList: instruction.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Instruction);
